package swinglayoutmanagement;

import java.awt.EventQueue;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The GridLayout layout manager lays out components in a rectangular grid. The container is divided into equally sized
 * rectangles. One component is placed in each rectangle.
 * 
 * This example shows a skeleton of a simple calculator tool.
 */
public class GridLayoutEx extends JFrame
{
    /**
     * Constructor.
     */
    public GridLayoutEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     * 
     * Nineteen buttons and one label are put into the manager. Each button is of the same size.
     * 
     * One of the constructors of the GridLayout manager takes four parameters: the number of rows, the number of
     * columns and the horizontal and vertical gaps between components.
     */
    private void initUI()
    {
        var panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setLayout(new GridLayout(5, 4, 5, 5));
        
        String[] buttons = { "Cls", "Bck", "", "Close", "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0",
                             ".", "=", "+" };
        
        for (int i = 0; i < buttons.length; i++)
        {
            if (i == 2)
                panel.add(new JLabel(buttons[i]));
            else
                panel.add(new JButton(buttons[i]));
        }
        
        add(panel);
        
        setTitle("GridLayout");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new GridLayoutEx();
            ex.setVisible(true);
        });
    }
    
}
