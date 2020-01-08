package swingevents;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * This example looks for a position of a window on the screen.
 * 
 * This example shows the current window coordinates on the pane. To get the window position, the ComponentListener is
 * used. The main class implements the ComponentListener interface. It has to provide implementation of all its methods.
 */
public class MovingWindowEx extends JFrame implements ComponentListener
{
    // Data Fields
    private JLabel labelx;
    private JLabel labely;
    
    // Methods
    /**
     * Constructor.
     */
    private MovingWindowEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        addComponentListener(this);
        
        labelx = new JLabel("x: ");
        labelx.setFont(new Font("Serif", Font.BOLD, 14));
        labelx.setBounds(20, 20, 60, 25);
        
        labely = new JLabel("y: ");
        labely.setFont(new Font("Serif", Font.BOLD, 14));
        labely.setBounds(20, 45, 60, 25);
        
        createLayout(labelx, labely);
        
        setTitle("Moving window");
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
    
    // All four methods must be implemented, even if only one of them is of interest: componentMoved(). The remaining
    // are empty.
    @Override
    public void componentResized(ComponentEvent e)
    {}
    
    @Override
    public void componentMoved(ComponentEvent e)
    {
        // Get the x and the y positions of the component.
        var x = e.getComponent().getX();
        var y = e.getComponent().getY();
        
        // Set the retrieved values to the labels.
        labelx.setText("x: " + x);
        labely.setText("y: " + y);
    }
    
    @Override
    public void componentShown(ComponentEvent e)
    {}
    
    @Override
    public void componentHidden(ComponentEvent e)
    {}
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new MovingWindowEx();
            ex.setVisible(true);
        });
    }
    
}
