package swingevents;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * An adapter is a convenient class that provides empty implementations for all required methods, so unnecessary coding
 * can be avoided by using adapters and implementing only those methods that are actually needed. There is no adapter
 * for a button click event because there there is only one method to implement: actionPerformed(). Adapters can be used
 * in situations where more than one method has to be implemented.
 * 
 * This example shows the current window coordinates on the pane using the ComponentAdapter.
 */
public class AdapterEx extends JFrame
{
    // Data Fields
    private JLabel labelx;
    private JLabel labely;
    
    // Methods
    /**
     * Constructor.
     */
    public AdapterEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        // Register the component listener.
        addComponentListener(new MoveAdapter());
        
        labelx = new JLabel("x: ");
        labelx.setFont(new Font("Serif", Font.BOLD, 14));
        
        labely = new JLabel("y: ");
        labely.setFont(new Font("Serif", Font.BOLD, 14));
        
        createLayout(labelx, labely);
        
        setTitle("Adapter example");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * Create the layout.
     */
    private void createLayout(JComponent... arg)
    {
        var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);
        
        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);
        
        gl.setHorizontalGroup(gl.createParallelGroup()
                .addComponent(arg[0])
                .addComponent(arg[1])
                .addGap(250)
        );
        
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
                .addComponent(arg[1])
                .addGap(130)
        );
        
        pack();
    }
    
    /**
     * MoveAdapter inner class.
     * 
     * Define the componentMoved() method. All the other methods are left empty.
     */
    private class MoveAdapter extends ComponentAdapter
    {
        @Override
        public void componentMoved(ComponentEvent e)
        {
            var x = e.getComponent().getX();
            var y = e.getComponent().getY();
            
            labelx.setText("x: " + x);
            labely.setText("y: " + y);
        }
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new AdapterEx();
            ex.setVisible(true);
        });
    }
    
}
