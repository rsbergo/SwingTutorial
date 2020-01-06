package swinglayoutmanagement;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Insets;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Using a BoxLayout manager allows to set a rigid area between the components.
 * 
 * This example displays four buttons. By default, there is no space between the buttons. To add some space among them,
 * add some rigid area.
 */
public class BoxLayoutRigidAreaEx extends JFrame
{
    /**
     * Constructor.
     */
    public BoxLayoutRigidAreaEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     * 
     * It uses a vertical BoxLayout manager for the panel.
     */
    private void initUI()
    {
        var basePanel = new JPanel();
        basePanel.setLayout(new BoxLayout(basePanel, BoxLayout.Y_AXIS));
        
        basePanel.setBorder(new EmptyBorder(new Insets(40, 60, 40, 60)));
        
        // Add buttons and create a rigid area between them with Box.createRigidArea().
        basePanel.add(new JButton("Button"));
        basePanel.add(Box.createRigidArea(new Dimension(0, 5)));
        basePanel.add(new JButton("Button"));
        basePanel.add(Box.createRigidArea(new Dimension(0, 5)));
        basePanel.add(new JButton("Button"));
        basePanel.add(Box.createRigidArea(new Dimension(0, 5)));
        basePanel.add(new JButton("Button"));
        
        add(basePanel);
        
        pack();
        
        setTitle("RigidArea");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new BoxLayoutRigidAreaEx();
            ex.setVisible(true);
        });
    }
    
}
