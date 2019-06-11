package probabilities;

import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.math3.distribution.PoissonDistribution;

/**
 *
 * @author ahbuss
 */
public class EBO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PoissonDistribution poiss = new PoissonDistribution(10.0);
        Map<Integer, Double> increments = new TreeMap<>();
        Map<Integer, Double> cumulative = new TreeMap<>();
        double epsilon = 1.0E-5;
        int s = 10;
        for (int x = 0; x < 30; ++x) {
            double increment = poiss.probability(x) * Math.max(0, x - s);
            increments.put(x, increment);
            if (x == 0) {
                cumulative.put(x, 0.0);
            } else {
                cumulative.put(x, increment + cumulative.get(x - 1));
            }

        }
        
        System.out.println("s = " + s);
        for (int x: increments.keySet()) {
            System.out.printf("%d\t%.3f\t%.3f%n", x, increments.get(x), cumulative.get(x));
        }
    }

}
