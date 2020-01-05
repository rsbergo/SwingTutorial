package swinglayoutmanagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * BorderLayout is the default layout manager for JFrame, JWindow, JDialog, JInternalFrame, and JApplet. It sets the
 * gaps between its children in pixels, thus creating rigid layouts. This leads to non-portable UI.
 * 
 * BorderLayout divides the space into five regions: north, west, south, east, and center. Each region can have only one
 * component. If we need to put more components into a region, we have to put a panel there with a manager of our
 * choice. The components in N, W, S, E regions get their preferred size. The component in the center takes up the whole
 * space left.
 * 
 * It does not look good if child components are too close to each other. We must put some space among them. Each
 * component in Swing toolkit can have borders around its edges. To create a border, we either create a new instance of
 * an EmptyBorder class or we use a BorderFactory.
 * 
 * This example displays a gray panel and border around it.
 */
public class BorderLayoutEx extends JFrame
{
    /**
     * Constructor.
     */
    public BorderLayoutEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     * 
     * The top panel is placed into the bottom panel. The bottom panel has the BorderLayout manager. The top panel is
     * placed into the center area of the bottom panel's BorderLayout manager.
     */
    private void initUI()
    {
        var bottomPanel = new JPanel(new BorderLayout());
        var topPanel = new JPanel();
        
        topPanel.setBackground(Color.gray);
        topPanel.setPreferredSize(new Dimension(250, 150));
        bottomPanel.add(topPanel);
        
        // Create a 20px border around the bottom panel.
        bottomPanel.setBorder(new EmptyBorder(new Insets(20, 20, 20, 20)));
        
        add(bottomPanel);
        pack();
        
        setTitle("BorderLayout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new BorderLayoutEx();
            ex.setVisible(true);
        });
    }
    
}
