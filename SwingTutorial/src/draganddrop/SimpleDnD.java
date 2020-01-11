package draganddrop;

import java.awt.EventQueue;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.TransferHandler;

/**
 * This example demonstrates a simple drag-and-drop operation working with built-in support and using a TransferHandler
 * class.
 * 
 * This example contains a text field and a button. It is possible to drag a text from the field and drop it onto the
 * button.
 */
public class SimpleDnD extends JFrame
{
    // Data Fields
    private JTextField field;
    private JButton button;
    
    // Methods
    /**
     * Constructor.
     */
    public SimpleDnD()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        setTitle("Simple Drag & Drop");
        
        button = new JButton("Button");
        field = new JTextField(15);
        
        // The text field has a built-in support for dragging that must be enabled.
        field.setDragEnabled(true);
        
        // The TransferHandler is a class responsible for transferring data between components. The constructor takes a
        // property name as a parameter.
        button.setTransferHandler(new TransferHandler("text"));
        
        createLayout(field, button);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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
        
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
                .addComponent(arg[1])
        );
        
        gl.setVerticalGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(arg[0])
                .addComponent(arg[1])
        );
        
        pack();
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> 
        {
            var ex = new SimpleDnD();
            ex.setVisible(true);
        });
    }
    
}
