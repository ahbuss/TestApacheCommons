package triangles.test;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import static triangles.MollerTrumbore.rayIntersectsTriangle;
import triangles.Triangle;

/**
 *
 * @author ahbuss
 */
public class TestMollerTrumbore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Triangle triangle = new Triangle(Vector3D.PLUS_I, Vector3D.PLUS_J, Vector3D.PLUS_K);
        Vector3D origin = new Vector3D(-1, -1, -1);
        
        Vector3D destination = new Vector3D(4, 5, 6);
        Vector3D ray = destination.subtract(origin);
        List<Vector3D> intersections = new ArrayList<>();
        
        boolean success = rayIntersectsTriangle(origin, ray, triangle, intersections);
        if (success) {
            System.out.printf("Intersection at %s%n", intersections.get(0));
        } else {
            System.out.println("No intersection");
        }
    }

}
