package quantiles;

import java.util.Arrays;
import java.util.Random;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;

/**
 *
 * @author ahbuss
 */
public class TestQuantile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Percentile percentile = new Percentile().withEstimationType(Percentile.EstimationType.R_8);
        double[] data = new double[13];
        for (int i = 0; i < data.length; ++i) {
            data[i] = data.length - i;
        }
        double p = 70;
        double q = percentile.evaluate(data, p);
        System.out.println(Arrays.toString(data));
        System.out.println(q);

        Random rng = new Random();
        for (int i = 0; i < 13; ++i) {
            data[i] = (rng.nextInt(50) + 50) * 0.01;
        }
        data = new double[]{66.0, 91.0, 58.0, 90.0, 86.0, 64.0, 81.0, 74.0, 86.0, 57.0, 53.0, 53.0, 81.0};
        q = percentile.evaluate(data, p);
        System.out.println(Arrays.toString(data));
        System.out.println(q);
        percentile = percentile.withEstimationType(Percentile.EstimationType.R_8);
        q = percentile.evaluate(data, p);
        System.out.println(Arrays.toString(data));
        System.out.println(q);

        double[] pVals = new double[]{25, 50, 75, 80, 90};
        System.out.print("|Type|");
        for (double pp : pVals) {
            System.out.printf("%.0f|", pp);
        }
        System.out.println();
        System.out.print("|---|");
        for (double pp : pVals) {
            System.out.printf("----|", pp);
        }
        System.out.println();
        for (int i = 0; i < Percentile.EstimationType.values().length; ++i) {
            Percentile.EstimationType type = Percentile.EstimationType.values()[i];
            if (type == Percentile.EstimationType.LEGACY) { continue;}
            percentile = percentile.withEstimationType(type);
            System.out.printf("|%s|", type);
            for (double pp : pVals) {
                q = percentile.evaluate(data, pp);
                System.out.printf("%,.2f|", q);
            }
            System.out.println();
        }
    }

}
