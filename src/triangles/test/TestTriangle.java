package triangles.test;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import triangles.Triangle;

/**
 *
 * @author ahbuss
 */
public class TestTriangle {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Vector3D v0 = new Vector3D(1, 2, 3);
        Vector3D v1 = new Vector3D(4, 5, 6);
        Vector3D v2 = new Vector3D(7, 8, 9);
        
        Triangle triangle = new Triangle(v0, v1, v2);
        System.out.println(triangle);
    }

}
