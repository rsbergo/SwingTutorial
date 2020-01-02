package firstprograms;

import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Tooltips are part of the internal applicatin's help system. Swing shows a small window if we hover a mouse pointer
 * over an object that has a tooltip set.
 * 
 * A tooltip is set for the frame and the button.
 */
public class TooltipEx extends JFrame
{
    /**
     * Constructor.
     */
    public TooltipEx()
    {
        initUI();
    }
    
    /**
     * Initializes the UI.
     */
    private void initUI()
    {
        var btn = new JButton("Button");
        
        // To enable a tooltip.
        btn.setToolTipText("A button component");
        
        createLayout(btn);
        
        setTitle("Tooltip");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * Creates the layout for this window.
     * 
     * A content pane is an instance of a JPanel component. The getContentPane() method returns a Container type. Since
     * setting a tooltip requires a JComponent instance, the object is cast to a JPanel.
     */
    private void createLayout(JComponent... arg)
    {
        var pane = (JPanel) getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);
        
        // A tooltip is set for the content pane.
        pane.setToolTipText("Content pane");
        
        // addGap() creates some space to the right and to the bottom of the button, increasing the initial size of the
        // window (set size was not called in initUI()).
        gl.setAutoCreateContainerGaps(true);
        gl.setHorizontalGroup(gl.createSequentialGroup().addComponent(arg[0]).addGap(200));
        gl.setVerticalGroup(gl.createSequentialGroup().addComponent(arg[0]).addGap(120));
        
        // Automatically sizes JFrame based on the size of its components. It takes the defined space into account. The
        // window will display the button and the spaces set with the addGap() method.
        pack();
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new TooltipEx();
            ex.setVisible(true);
        });
    }
    
}
