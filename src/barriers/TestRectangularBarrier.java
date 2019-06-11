package barriers;

import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;

/**
 * @version $Id$
 * @author ahbuss
 */
public class TestRectangularBarrier {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Rectangle2D barrier = new Rectangle2D.Double(10.0, -8.0, 20.0, 11.0);
        System.out.println(barrier);
        PathIterator pi = barrier.getPathIterator(null);
        double[] coords = new double[4];
        while (!pi.isDone()) {
            pi.currentSegment(coords);
            System.out.println(Arrays.toString(coords));
            pi.next();
        }
    }

}
