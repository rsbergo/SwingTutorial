package basicswingcomponents2;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * A JTextArea is a multiline text area that displays plain text. It is lightweight component for working with text. The
 * component does not handle scrolling. For this task, use a JScrollPane component.
 * 
 * This example shows a simple JTextArea component.
 */
public class TextAreaEx extends JFrame
{
    /**
     * Constructor.
     */
    public TextAreaEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        // Create the JTextArea component. To make the text scrollable, put the JTextArea component into the SCrollPane
        // component.
        var area = new JTextArea();
        var spane = new JScrollPane(area);
        
        // The setLineWrap() method makes the lines wrapped if they are too long to fit the text area's width.
        area.setLineWrap(true);
        
        // Specify how line is going to be wrapped (true for word boundaries - white spaces).
        area.setWrapStyleWord(true);
        
        createLayout(spane);
        
        setTitle("JTextArea");
        setSize(new Dimension(350, 300));
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
        
        gl.setHorizontalGroup(gl.createParallelGroup()
                .addComponent(arg[0])
        );
        
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );
        
        pack();
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> 
        {
            var ex = new TextAreaEx();
            ex.setVisible(true);
        });
    }
    
}
