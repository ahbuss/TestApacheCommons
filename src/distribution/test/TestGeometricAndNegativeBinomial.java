package distribution.test;

import distribution.GeometricDistribution;
import distribution.NegativeBinomialDistribution;
import java.util.Random;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 *
 * @author ahbuss
 */
public class TestGeometricAndNegativeBinomial {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GeometricDistribution geom = new GeometricDistribution(.2);
                DescriptiveStatistics stat = new DescriptiveStatistics();
        int number = 1000000;
        Random rand = new Random();
        for (int i = 0; i < number; ++i) {
            stat.addValue(geom.inverseCumulativeProbability(rand.nextDouble()));
        }
        System.out.println(geom);
        System.out.printf("actual mean = %.3f actual var = %.3f%n", geom.getNumericalMean(), geom.getNumericalVariance());
        System.out.printf("count = %,d mean = %.3f var = %.3f%n", 
                stat.getN(), stat.getMean(), stat.getVariance());
        
        NegativeBinomialDistribution negBin = new NegativeBinomialDistribution(3.0, 0.5);
        stat.clear();
        for (int i = 0; i < number; ++i) {
            stat.addValue(negBin.inverseCumulativeProbability(rand.nextDouble()));
        }
        System.out.println(negBin);
        System.out.printf("actual mean = %.3f actual var = %.3f%n", negBin.getNumericalMean(), negBin.getNumericalVariance());
        System.out.printf("count = %,d mean = %.3f var = %.3f%n", 
                stat.getN(), stat.getMean(), stat.getVariance());
        
        negBin.setNumberSuccesses(5.0);
        negBin.setProbabilityOfSuccess(0.6);
        stat.clear();
        for (int i = 0; i < number; ++i) {
            stat.addValue(negBin.inverseCumulativeProbability(rand.nextDouble()));
        }
        System.out.println(negBin);
        System.out.printf("actual mean = %.3f actual var = %.3f%n", negBin.getNumericalMean(), negBin.getNumericalVariance());
        System.out.printf("count = %,d mean = %.3f var = %.3f%n", 
                stat.getN(), stat.getMean(), stat.getVariance());  
        
        System.out.println(negBin.cumulativeProbability(0));
    }
    
}
