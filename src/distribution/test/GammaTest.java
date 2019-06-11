package distribution.test;

import static java.lang.Math.pow;
import static org.apache.commons.math3.special.Gamma.gamma;

/**
 *
 * @author ahbuss
 */
public class GammaTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double scale = 3.0;
        double shape = 2.0;
        double mean = scale * gamma(1.0 + 1.0/shape);
        double var = pow(scale, 2) * (gamma(1.0 + 2.0/shape) - pow(gamma(1.0 + 1./shape),2));
        System.out.printf("μ = %.4f σ² = %.4f%n", mean, var);
    }

}
