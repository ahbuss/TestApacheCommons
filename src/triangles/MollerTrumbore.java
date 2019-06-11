package triangles;

import java.util.List;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 *
 * @author ahbuss
 */
public class MollerTrumbore {

    public static final double EPSILON = 1.0E-8;
    
    public static boolean rayIntersectsTriangle(
            Vector3D rayOrigin,
            Vector3D rayVector,
            Triangle triangle,
            List<Vector3D> outIntersectionPoint) {
        boolean intersects = false;
        
        Vector3D vertex0 = triangle.getVertex0();
        Vector3D vertex1 = triangle.getVertex1();
        Vector3D vertex2 = triangle.getVertex2();
        
        Vector3D edge1 = vertex1.subtract(vertex0);
        Vector3D edge2 = vertex2.subtract(vertex0);
        
        Vector3D h = rayVector.crossProduct(edge2);
        
        double a = edge1.dotProduct(h);
        
        
        if (a < -EPSILON || a > EPSILON) {
            double f = 1.0 / a;
            
            Vector3D s = rayOrigin.subtract(vertex0);
            double u = f * s.dotProduct(h);
            
            if (u >= 0.0 && u <= 1.0) {
                Vector3D q = s.crossProduct(edge1);
                double v = f * rayVector.dotProduct(q);
                
                if (v >= 0.0 && u + v <= 1.0) {
                    double t = f * edge2.dotProduct(q);
                    if (t > EPSILON) {
                        Vector3D intersection = new Vector3D(1.0, rayOrigin, t, rayVector);
                        outIntersectionPoint.add(intersection);
                        intersects = true;
                    }
                }
            }
        }
        
        return intersects;
    }

}
