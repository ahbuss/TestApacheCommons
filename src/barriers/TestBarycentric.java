package barriers;

import java.awt.geom.Point2D;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

/**
 * @version $Id$
 * @author ahbuss
 */
public class TestBarycentric {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Point2D y0 = new Point2D.Double(5.0, 40.0);
        Point2D y1 = new Point2D.Double(50.0, 45.0);
        Point2D xS = new Point2D.Double(20.0, 10.0);

        Point2D xT = new Point2D.Double(25.0, 35.0);

        double[][] lhsValues = {
            {y0.getX(), y1.getX(), xS.getX()},
            {y0.getY(), y1.getY(), xS.getY()},
            {1.0, 1.0, 1.0}
        };

        double[] rhsValues = {xT.getX(), xT.getY(), 1.0};
        RealVector rhs = new ArrayRealVector(rhsValues);

        RealMatrix coeff = new Array2DRowRealMatrix(lhsValues);

        DecompositionSolver solver = new LUDecomposition(coeff).getSolver();

        RealVector solution = solver.solve(rhs);

        System.out.println(solution);

        rhs.setEntry(1, 50.0);

        solution = solver.solve(rhs);
        System.out.println(solution);

        rhs.setEntry(0, 40.0);
        rhs.setEntry(1, 30.0);
        solution = solver.solve(rhs);
        System.out.println(solution);
        rhs.setEntry(0, 0.0);
        rhs.setEntry(1, 0.0);
        solution = solver.solve(rhs);
        System.out.println(solution);
        
//        Test of solve() method - should match first one 
        solution = solve(new Point2D[] { y0, y1, xS}, xT);
        System.out.println(solution);
        
        Point2D[] triangle = {
            new Point2D.Double(0.0, 10.0),
            new Point2D.Double(10.0, 0.0),
            new Point2D.Double(0.0, 0.0)
        };
        
        Point2D[] targets = {
            new Point2D.Double(5.0, 4.0),
            new Point2D.Double(16.0, 17.0),
            new Point2D.Double(-5.0, 14.0),
            new Point2D.Double(14.0, -5.0),
            new Point2D.Double(-5.0, 4.0),
            new Point2D.Double(4.0, -5.0),
            new Point2D.Double(-5.0, -5.0)
        };
        
        int index = 0;
        for (Point2D target: targets) {
            System.out.printf("%d: x_T=(%.2f, %.2f) λ=%s%n",
                    (++index), target.getX(), target.getY(),
                    solve(triangle, target));
        }
        
    }

    public static RealVector solve(Point2D[] verticies, Point2D target) {
        double[][] lhsValues = {
            {verticies[0].getX(), verticies[1].getX(), verticies[2].getX()},
            {verticies[0].getY(), verticies[1].getY(), verticies[2].getY()},
            {1.0, 1.0, 1.0}
        };
        RealMatrix coeff = new Array2DRowRealMatrix(lhsValues);
        DecompositionSolver solver = new LUDecomposition(coeff).getSolver();
        double[] rhsValues = {target.getX(), target.getY(), 1.0};
        RealVector rhs = new ArrayRealVector(rhsValues);
        RealVector solution = solver.solve(rhs);
        return solution;
//        return solution.toArray();
    }

}
