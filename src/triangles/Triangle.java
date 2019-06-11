package triangles;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 *
 * @author ahbuss
 */
public class Triangle {

    private final Vector3D vertex0;
    private final Vector3D vertex1;
    private final Vector3D vertex2;
    
    public Triangle(Vector3D vertex0, Vector3D vertex1, Vector3D vertex2) {
        this.vertex0 = vertex0;
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }

    /**
     * @return the vertex0
     */
    public Vector3D getVertex0() {
        return vertex0;
    }

    /**
     * @return the vertex1
     */
    public Vector3D getVertex1() {
        return vertex1;
    }

    /**
     * @return the vertex2
     */
    public Vector3D getVertex2() {
        return vertex2;
    }
    
    public String toString() {
        return String.format("{%s, %s, %s}", vertex0, vertex1, vertex2);
    }

}
