package swinglayoutmanagement;

import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * It is possible to go without a layout manager. There might be a few situations where a layout manager is not needed
 * (e.g. positioning some images at some irregular locations). But in most cases to create truly portable, complex
 * applications, layout managers are needed. Without any manager, components are positioned using absolute values.
 * 
 * This example shows two buttons.
 */
public class AbsoluteLayoutEx extends JFrame
{
    /**
     * Constructor.
     */
    public AbsoluteLayoutEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        // To use absolute positioning, provide null to the setLayout() method. (The JFrame component has a default
        // layout manager, the BorderLayout).
        setLayout(null);
        
        // The setBounds() method positions the OK button. The parameters are the x and y coordinates and the width and
        // height of the component.
        var okBtn = new JButton("OK");
        okBtn.setBounds(50, 50, 80, 25);
        
        var closeBtn = new JButton("Close");
        closeBtn.setBounds(150, 50, 80, 25);
        
        add(okBtn);
        add(closeBtn);
        
        setTitle("Absolute positioning");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new AbsoluteLayoutEx();
            ex.setVisible(true);
        });
    }
    
}
