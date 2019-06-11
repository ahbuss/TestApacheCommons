package distribution.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author ahbuss
 */
public class ShuffleTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random rng = new Random();
        rng.setSeed(12345L);
        
        List<Integer> original = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            original.add(rng.nextInt(10));
        }
        
        System.out.println(original);
        
        List<Integer> copy = new LinkedList<>(original);
        Collections.shuffle(copy, rng);
        System.out.println(copy);
        
        
    }
    
}
