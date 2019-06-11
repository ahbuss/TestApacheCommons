package quantiles;

import java.util.Random;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.PoissonDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 *
 * @author ahbuss
 */
public class TestQuantiles {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NormalDistribution norm = new NormalDistribution(5.0, 2.1);
        DescriptiveStatistics stat = new DescriptiveStatistics();
        int number = 1000000;
        Random rand = new Random();
        for (int i = 0; i < number; ++i) {
            stat.addValue(norm.inverseCumulativeProbability(rand.nextDouble()));
        }

        System.out.printf("Normal: mean = %.3f, std = %3f%n", stat.getMean(), stat.getStandardDeviation());
        stat.clear();

        PoissonDistribution poiss = new PoissonDistribution(5.5);
        for (int i = 0; i < number; ++i) {
            stat.addValue(poiss.inverseCumulativeProbability(rand.nextDouble()));
        }
        System.out.printf("Poisson: mean = %.3f, std = %3f var = %f%n", stat.getMean(),
                stat.getStandardDeviation(), stat.getVariance());
        stat.clear();

        poiss = new PoissonDistribution(25.6);
        for (int i = 0; i < number; ++i) {
            stat.addValue(poiss.inverseCumulativeProbability(rand.nextDouble()));
        }
        System.out.printf("Poisson: mean = %.3f, std = %3f var = %f%n", stat.getMean(),
                stat.getStandardDeviation(), stat.getVariance());
        
        poiss = new PoissonDistribution(25.16);
        double glMin = 0.5;
        double glMax = 0.99;
        
        int min = poiss.inverseCumulativeProbability(glMin);
        int max = poiss.inverseCumulativeProbability(glMax);
        System.out.printf("min: %d max: %d%n", min, max);
    }

}
