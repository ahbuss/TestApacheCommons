package quantiles;

import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;

/**
 *
 * @author ahbuss
 */
public class TestTukeyOutlier {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random rng = new Random();
        
        SortedSet<Double> values = new TreeSet<>();
        
        for (int i = 1; i < 100; ++i) {
            values.add(i * 1.0);
        }
        System.out.println(values);
        
        double[] valArray = new double[values.size()];
        int index = 0;
        for (double val: values) {
            valArray[index++] = val;
        }
        
        Percentile percentile = new Percentile();
        
        double q1 = percentile.evaluate(valArray, 25);
        double q3 = percentile.evaluate(valArray, 75);
        
        System.out.printf("q1 = %f, q3 = %f%n", q1, q3);
    }
    
}
