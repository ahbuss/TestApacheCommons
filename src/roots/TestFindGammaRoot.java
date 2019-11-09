package roots;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.BrentSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import static org.apache.commons.math3.special.Gamma.gamma;
import static org.apache.commons.math3.special.Gamma.logGamma;
import static org.apache.commons.math3.util.FastMath.exp;
import static org.apache.commons.math3.util.FastMath.log;

/**
 *
 * @author ahbuss
 */
public class TestFindGammaRoot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        UnivariateFunction function = // some user defined function object
final double relativeAccuracy = 1.0e-12;
final double absoluteAccuracy = 1.0e-8;
UnivariateSolver nonBracketing = new BrentSolver(relativeAccuracy, absoluteAccuracy);
double baseRoot = nonBracketing.solve(100, function, 1.0, 5.0);
double c = UnivariateSolverUtils.forceSide(100, function,
                                           new PegasusSolver(relativeAccuracy, absoluteAccuracy),
                                           baseRoot, 1.0, 5.0, AllowedSolution.LEFT_SIDE);
         */
        final double relativeAccuracy = 1.0e-12;
        final double absoluteAccuracy = 1.0e-8;
        UnivariateSolver nonBracketing = new BrentSolver(relativeAccuracy, absoluteAccuracy);

        double lambda = 2.0;
        double k = 3.0;
        double mean = lambda * gamma(1.0 + 1.0 / k);
        double variance = lambda * lambda * gamma(1.0 + 2.0 / k) - mean * mean;
        double ratio = variance / (mean * mean) + 1.0;
        GammaRatio function = new GammaRatio(ratio);
        GammaRatio2 function2 = new GammaRatio2(ratio);

        double x = function.value(k);
        double x2 = function2.value(k);

//        System.out.printf("lambda = %f, k=%f, mean=%f,var=%f%n", lambda, k, mean, variance);
//        System.out.printf("ratio: %f, from function: %f function2: %f%n", (variance / (mean * mean) + 1.0), x, x2);
//
        double baseRoot = nonBracketing.solve(100, function, 1, 5.0);

        System.out.printf("mean=%f, variance=%f, root=%f%n", mean, variance, baseRoot);
        mean = 4.0;
        variance = 2.0;
        ratio = variance / (mean * mean) + 1.0;
        function = new GammaRatio(ratio);
        baseRoot = nonBracketing.solve(100, function2, 1, 5.0);
//        System.out.printf(" mean=%f, variance=%f, root=%f%n", mean, variance, baseRoot);
        k = baseRoot;
        lambda = mean / gamma(1.0 + 1.0 / k);

        double estMean = lambda * gamma(1.0 + 1.0 / k);
        double estVar = lambda * lambda * gamma(1.0 + 2.0 / k) - mean * mean;

        System.out.printf("Using Lancosz: estMean: %f, estVar: %f, lambda: %f, k: %f%n", estMean, estVar, lambda, k);
        baseRoot = nonBracketing.solve(100, function, 1, 5.0);

        k = baseRoot;
        lambda = mean / gamma(1.0 + 1.0 / k);

        estMean = lambda * gamma(1.0 + 1.0 / k);
        estVar = lambda * lambda * gamma(1.0 + 2.0 / k) - mean * mean;
        System.out.printf("Using Apache: estMean: %f, estVar: %f, lambda: %f, k: %f%n", estMean, estVar, lambda, k);

//        for (double xx = 1.0; xx < 5; xx += 0.1) {
//            System.out.printf("Apache logGamma(%.1f): %f Lancsoz logGamma(%.1f): %f diff: %f%n", 
//                    xx, function.value(xx), xx, function2.value(xx), function.value(xx) - function2.value(xx));
//        }
    }

    private static class GammaRatio implements UnivariateFunction {

        private double ratio;

        public GammaRatio(double ratio) {
            this.ratio = ratio;
        }

        @Override
        public double value(double x) {
            double value;
            double logValue = logGamma(1.0 + 2.0 / x) - 2.0 * logGamma(1.0 + 1.0 / x);
            System.out.printf("Apache x: %f, logValue: %f%n", x, logValue);
            value = exp(logValue);
            return value - ratio;
        }

        /**
         * @return the ratio
         */
        public double getRatio() {
            return ratio;
        }

        /**
         * @param ratio the ratio to set
         */
        public void setRatio(double ratio) {
            this.ratio = ratio;
        }

    }

    private static class GammaRatio2 implements UnivariateFunction {

        private double ratio;

        public GammaRatio2(double ratio) {
            this.ratio = ratio;
        }

        @Override
        public double value(double x) {
            double value;
            double logValue = this.logGamma(1.0 + 2.0 / x) - 2.0 * this.logGamma(1.0 + 1.0 / x);
            System.out.printf("Lanczos x: %f, logValue: %f%n", x, logValue);

            value = exp(logValue);
            return value - ratio;
        }

        public double logGamma(double xx) {
            double x, y, tmp, ser;
            x = y = tmp = ser = 0.0;

            final double[] cof = {76.18009172947146, -86.50532032941677,
                24.01409824083091, -1.231739572450155, 0.1208650973866179e-2,
                -0.5395239384953e-5};
            int j;
            y = x = xx;
            tmp = x + 5.5;
            tmp -= (x + 0.5) * log(tmp);
            ser = 1.000000000190015;
            for (j = 0; j <= 5; j++) {
                ser += cof[j] / ++y;
            }

            return -tmp + log(2.5066282746310005 * ser / x);
        }

        double gamma(double x) {
            return exp(this.logGamma(x));
        }

        /**
         * @return the ratio
         */
        public double getRatio() {
            return ratio;
        }

        /**
         * @param ratio the ratio to set
         */
        public void setRatio(double ratio) {
            this.ratio = ratio;
        }
    }

}
