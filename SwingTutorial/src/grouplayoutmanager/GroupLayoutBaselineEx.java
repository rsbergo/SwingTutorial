package grouplayoutmanager;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import java.awt.EventQueue;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Baseline alignment is aligning components along the baseline of the text the they contain.
 * 
 * This example contains a label and a combo box. Both components contain text and are aligned along the baseline of
 * their text.
 */
public class GroupLayoutBaselineEx extends JFrame
{
    // Data Fields
    private JLabel display;
    private JComboBox box;
    private String[] distros;
    
    // Methods
    /**
     * Constructor.
     */
    public GroupLayoutBaselineEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);
        
        distros = new String[] { "Easy", "Medium", "Hard" };
        box = new JComboBox<>(distros);
        display = new JLabel("Level:");
        
        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);
        
        gl.setHorizontalGroup(gl.createSequentialGroup().
                addComponent(display).
                addComponent(box, 
                             GroupLayout.DEFAULT_SIZE, 
                             GroupLayout.DEFAULT_SIZE, 
                             GroupLayout.PREFERRED_SIZE));
        
        // The baseline alignment is achieved with the BASELINE parameter passed to the createParallelGroup() method.
        gl.setVerticalGroup(gl.createParallelGroup(BASELINE)
                .addComponent(box, 
                              GroupLayout.DEFAULT_SIZE, 
                              GroupLayout.DEFAULT_SIZE, 
                              GroupLayout.PREFERRED_SIZE)
                .addComponent(display));
        
        pack();
        
        setTitle("Baseline alignment");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new GroupLayoutBaselineEx();
            ex.setVisible(true);
        });
    }
    
}
