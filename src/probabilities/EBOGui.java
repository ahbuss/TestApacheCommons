package probabilities;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.apache.commons.math3.distribution.PoissonDistribution;

/**
 *
 * @author ahbuss
 */
public class EBOGui extends JFrame implements Runnable {

    private static final String HEADING = 
            " s\tEBO(s)\tEBO(s - 1)-EBO(s)\tPr{X >= s)" +
                                 "\n -\t------\t-------------\t------------";
    
    private JPanel inputPanel;
    private JButton computeButton;
    private JLabel expectedDemandLabel;
    private JTextField expectedDemandField;
    private JLabel numberStockLevelsLabel;
    private JTextField numberStockLevelsField;
    private JTextArea outputArea;
    
    private PoissonDistribution poisson;
    
    public EBOGui() {
        super("Expected Backorders");
        inputPanel = new JPanel(new GridLayout(0, 2));
        expectedDemandLabel = new JLabel("Expected Demand:", JLabel.RIGHT);
        expectedDemandField = new JTextField(10);
        numberStockLevelsLabel = new JLabel("# Stock Levels:", JLabel.RIGHT);
        numberStockLevelsField = new JTextField(10);
        computeButton = new JButton("Compute");
        inputPanel.add(expectedDemandLabel);
        inputPanel.add(expectedDemandField);
        inputPanel.add(numberStockLevelsLabel);
        inputPanel.add(numberStockLevelsField);
        inputPanel.add(computeButton);
        
        computeButton.addActionListener(new ComputeActionListener());
        
        this.getContentPane().add(inputPanel, BorderLayout.NORTH);
        
        outputArea = new JTextArea(HEADING);
        this.getContentPane().add(outputArea);
        
        this.setSize(500, 500);
        this.setLocation(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EBOGui eboGUI = new EBOGui();
        Thread thread = new Thread(eboGUI);
        thread.start();
    }

    @Override
    public void run() {
        this.setVisible(true);
    }
    
    private class ComputeActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            double mean = Double.parseDouble(expectedDemandField.getText());
            poisson = new PoissonDistribution(mean);
            int numberStockLevels = Integer.parseInt(numberStockLevelsField.getText());
            outputArea.setText(HEADING);
            for (int s = 0; s <= numberStockLevels; ++s) {
                double increment = s == 0 ? 0 : ebo(s - 1, poisson) - ebo(s, poisson);
                double byProb = s == 0 ? 0 : 1.0 - poisson.cumulativeProbability(s - 1);
                outputArea.append(String.format("%n %d\t%.3f\t%.3f\t\t%.3f", s, ebo(s, poisson),
                        increment, byProb));
            }
        }
        
    }
    
    private double ebo(int s, PoissonDistribution poiss) {
        double ebo = 0.0;
        int numberIterations = 20;
        
        for (int x = 0; x < numberIterations; ++x) {
            ebo += Math.max(0, x - s) * poiss.probability(x);
        }
        return ebo;
    }

}
