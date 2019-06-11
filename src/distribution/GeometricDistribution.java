package distribution;

import org.apache.commons.math3.distribution.AbstractIntegerDistribution;
import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.util.FastMath;

/**
 * This version is for the number of trials until success.
 * @author ahbuss
 */
public class GeometricDistribution extends AbstractIntegerDistribution  {

    private double probabilityOfSuccess; // p
    
    private double logP;
    private double log1MinusP;
    
    public GeometricDistribution(double p) {
        super(new MersenneTwister());
        this.setProbabilityOfSuccess(p);
    }
    
    @Override
    public double probability(int x) {
        double logProb = logP + (x - 1) * log1MinusP;
        return FastMath.exp(logProb);
    }

    @Override
    public double cumulativeProbability(int x) {
        double logInvCum = x * log1MinusP;
        return 1.0 - FastMath.exp(logInvCum);
    }

    @Override
    public double getNumericalMean() {
        return 1.0 / probabilityOfSuccess;
    }

    @Override
    public double getNumericalVariance() {
        return (1.0 - probabilityOfSuccess)/ (probabilityOfSuccess * probabilityOfSuccess);
    }

    @Override
    public int getSupportLowerBound() {
        return 1;
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
            throw new IllegalArgumentException(
                    "p must be \u2208 (0.0, 1.0): " + probabilityOfSuccess);
        }
        this.probabilityOfSuccess = probabilityOfSuccess;
        this.logP = FastMath.log(probabilityOfSuccess);
        this.log1MinusP = FastMath.log1p(-probabilityOfSuccess);
    }
    
    @Override
    public String toString() {
        return String.format("Geometric (%.3f)", getProbabilityOfSuccess());
    }
    
}
