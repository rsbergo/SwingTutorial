package basicswingcomponents2;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

/**
 * JTextPane component is a more advanced component for working with text. The component can do some complex formatting
 * operations over the text. It can also display HTML documents. The component does not handle scrolling.
 * 
 * This example shows a JTextPane component and loads a HTML document. The HTML document is loaded from the current
 * working directory. This example shows formatting capabilities of the component.
 */
public class TextPaneEx extends JFrame
{
    // Data Fields
    private JTextPane textPane;
    
    // Methods
    /**
     * Constructor.
     */
    public TextPaneEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        // Create a JTextPane component, set the content to be a HTML document, and disable editing.
        textPane = new JTextPane();
        var spane = new JScrollPane(textPane);
        
        textPane.setContentType("text/html");
        textPane.setEditable(false);
        
        loadFile();
        
        createLayout(spane);
        
        setTitle("JTextPane");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        );
        
        gl.setVerticalGroup(gl.createParallelGroup()
                .addComponent(arg[0])
        );
        
        pack();
    }
    
    /**
     * Load the HTML file.
     * 
     * Determine the current working directory of the user and load a HTML document into the pane.
     */
    private void loadFile()
    {
        try 
        {
            var curDir = System.getProperty("user.dir") + "/";
            textPane.setPage("File:///" + curDir + "test.html");
        }
        catch (IOException ex)
        {
            Logger.getLogger(this.getName()).log(Level.SEVERE, "Failed to load file", ex);
        }
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> 
        {
            var ex = new TextPaneEx();
            ex.setVisible(true);
        });
    }
    
}
