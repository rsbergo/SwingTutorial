package swinglayoutmanagement;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTree;

/**
 * The FlowLayout manager is the simplest layout manager in the Java Swing toolkit. It is the default layout manager for
 * the JPanel component. When calculating its children size, a FlowLayout lets each component assume its natural
 * (preferred) size. The manager puts components into a row in the order they were added; if a component does not fit
 * into a row, it goes into the next one. Components can be added from the right to the left or from the left to the
 * right. The manager allows to align the components, but implicitly the components are centered and there is a 5px
 * space between components and components and the edges of the container.
 * 
 * The example shows a button, a tree component, and a text area component in the window.
 */
public class FlowLayoutEx extends JFrame
{
    /**
     * Constructor.
     */
    public FlowLayoutEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     * 
     * The implicit layout manager of the JPanel component is FlowLayout. It does not need to be set manually. The
     * FlowLayout manager sets a preferred size for its components.
     */
    private void initUI()
    {
        var panel = new JPanel();
        
        var button = new JButton("button");
        panel.add(button);
        
        // If we create an empty tree component, there are some default values inside the component.
        var tree = new JTree();
        panel.add(tree);
        
        // Without setting the preferred size, the component would have a size of its text. Without the text, the
        // component would not be visible at all. Writing or deleting some text in the component makes it grow and
        // shrink accordingly.
        var area = new JTextArea("text area");
        area.setPreferredSize(new Dimension(100, 100));
        panel.add(area);
        
        add(panel);
        pack();
        
        setTitle("FlowLayout example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new FlowLayoutEx();
            ex.setVisible(true);
        });
    }
    
}
