package distribution;

import org.apache.commons.math3.distribution.AbstractIntegerDistribution;
import org.apache.commons.math3.random.MersenneTwister;
import static org.apache.commons.math3.special.Beta.regularizedBeta;
import static org.apache.commons.math3.special.Gamma.logGamma;
import static org.apache.commons.math3.util.FastMath.exp;
import static org.apache.commons.math3.util.FastMath.log;

/**
 *
 * @author ahbuss
 */
public class NegativeBinomialDistribution extends AbstractIntegerDistribution {

    private double numberSuccesses; //r

    private double probabilityOfSuccess; //p

    public NegativeBinomialDistribution(double numberSuccesses, double probabilityOfSuccess) {
        super(new MersenneTwister());
        this.setNumberSuccesses(numberSuccesses);
        this.setProbabilityOfSuccess(probabilityOfSuccess);
    }

    @Override
    public double probability(int x) {
        double logPMF = logGamma(x + numberSuccesses) - logGamma(x + 1)
                - log(numberSuccesses) + numberSuccesses * log(1.0 - probabilityOfSuccess)
                + x * log(probabilityOfSuccess);
        return exp(logPMF);
    }

    /**
     * Uses the fact that F(x) = 1 - I<sub>p</sub>(x + 1, r)
     * @param x Given value at which to evaluate cdf
     * @return F(x) the cdf of the Negative Binomial at x
     */
    @Override
    public double cumulativeProbability(int x) {
        if (x <= 0) {
            return 0.0;
        } else {
            return 1.0 - regularizedBeta(probabilityOfSuccess, x + 1, numberSuccesses);
        }
    }

    @Override
    public double getNumericalMean() {
        return probabilityOfSuccess * numberSuccesses / (1.0 - probabilityOfSuccess);
    }

    @Override
    public double getNumericalVariance() {
        return probabilityOfSuccess * numberSuccesses
                / ((1.0 - probabilityOfSuccess) * (1.0 - probabilityOfSuccess));

    }

    @Override
    public int getSupportLowerBound() {
        return 0;
    }

    @Override
    public int getSupportUpperBound() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isSupportConnected() {
        return true;
    }

    /**
     * @return the numberSuccesses
     */
    public double getNumberSuccesses() {
        return numberSuccesses;
    }

    /**
     * @param numberSuccesses the numberSuccesses to set
     */
    public final void setNumberSuccesses(double numberSuccesses) {
        if (numberSuccesses <= 0.0) {
            throw new IllegalArgumentException("Number of successes must be > 0.0: "
                    + numberSuccesses);
        }
        this.numberSuccesses = numberSuccesses;
    }

    /**
     * @return the probabilityOfSuccess
     */
    public double getProbabilityOfSuccess() {
        return probabilityOfSuccess;
    }

    /**
     * @param probabilityOfSuccess the probabilityOfSuccess to set
     */
    public final void setProbabilityOfSuccess(double probabilityOfSuccess) {
        if (probabilityOfSuccess <= 0.0 || probabilityOfSuccess >= 1.0) {
            throw new IllegalArgumentException("probabilityOfSuccess must be \u2208 (0.0, 1.0): "
                    + probabilityOfSuccess);
        }
        this.probabilityOfSuccess = probabilityOfSuccess;
    }

    @Override
    public String toString() {
        return String.format("Negative Binomial(%.1f, %.3f)", numberSuccesses, probabilityOfSuccess);
    }

}
