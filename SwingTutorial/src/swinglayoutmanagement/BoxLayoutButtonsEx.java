package swinglayoutmanagement;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * BoxLayout manager organizes components in a column or a row. It can create quite sophisticated layouts with nesting.
 * However, this raises the complexity of the layout creation and uses additional resources, notably many other JPanel
 * components. BoxLayout is only able to create fixed spaces; therefore, its layouts are not portable.
 * 
 * The constructor creates a layout manager that will lay out components along the given axis. Unlike other layout
 * managers, BoxLayout takes a container instance as the first parameter in the constructor. The second parameter
 * determines the orientation of the manager: to create a horizontal box, the LINE_AXIS constant can be used; to create
 * a vertical box, the PAGE_AXIS constant can be used.
 * 
 * The BoxLayout manager is often used with the Box class. This class creates several invisible components, which affect
 * the final layout: glue, strut, rigid area.
 */
public class BoxLayoutButtonsEx extends JFrame
{
    /**
     * Constructor.
     */
    public BoxLayoutButtonsEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     * 
     * Two panels are created. The base panel has a vertical box layout, the bottom panel has a horizontal one. The
     * bottom panel is put into the base panel. The bottom panel is right aligned. The space between the top of the
     * window and the bottom pane is expandable. This is achieved by the vertical glue.
     */
    private void initUI()
    {
        add(createBasePanel());
        
        setTitle("Two Buttons");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    /**
     * Create the bottom panel with the horizontal BoxLayout.
     * 
     * The bottom panel is right aligned, done by the setAlignmentX() method. The panel has a horizontal layout. Add
     * some rigid space between the buttons and.
     */
    private JPanel createBottomPanel()
    {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setAlignmentX(RIGHT_ALIGNMENT); // original: 1f. It seems that the alignment is from 0f to 1f.
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        
        // Add components in the following order: [OK] [Rigid area] [Close] [Rigid area]
        bottomPanel.add(new JButton("OK"));
        bottomPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        bottomPanel.add(new JButton("Close"));
        bottomPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        
        return bottomPanel;
    }
    
    /**
     * Create the base panel with the vertical BoxLayout.
     */
    private JPanel createBasePanel()
    {
        JPanel basePanel = new JPanel();
        basePanel.setLayout(new BoxLayout(basePanel, BoxLayout.Y_AXIS));
        
        // Add components in the following order:
        //     [Glue]
        //     [Bottom Panel]
        //     [Rigid area]
        basePanel.add(Box.createVerticalGlue());
        basePanel.add(createBottomPanel());
        basePanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        return basePanel;
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new BoxLayoutButtonsEx();
            ex.setVisible(true);
        });
    }
    
}
