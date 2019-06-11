package barriers;

import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.util.Arrays;

/**
 * @version $Id$
 * @author ahbuss
 */
public class TestTriangleBarrier {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Shape shape;
        
        Path2D triangle = new Path2D.Double();
        triangle.moveTo(10.0, 20.0);
        triangle.lineTo(30.0, 5.0);
        triangle.lineTo(5.0, 10.0);
        triangle.closePath();
        
        PathIterator pi = triangle.getPathIterator(null);
        
        double[] coords = new double[2];
        while (!pi.isDone()) {
            pi.currentSegment(coords);
            System.out.println(Arrays.toString(coords));
            pi.next();
        }
        
    }

}
