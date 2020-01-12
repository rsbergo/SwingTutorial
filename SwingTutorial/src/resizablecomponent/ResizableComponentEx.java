package resizablecomponent;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * In order to create a component that can be freely dragged over a panel, a panel with absolute positioning enabled
 * must be used.
 * 
 * This example creates a component that can be freely moved over a parent window and resized. Eight small rectangles
 * are drawn on the border of the resizable component when it has the focus. The rectangles serve as dragging points,
 * where the component can be drawn and start resizing.
 * 
 * This class sets up the panel and the component.
 */
public class ResizableComponentEx extends JFrame
{
    // Data Fields
    private Resizable res;
    
    // Methods
    /**
     * Constructor.
     */
    public ResizableComponentEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        // Use absolute positioning for a resizable component. By providing null to the constructor of a JPanel, a panel
        // with absolute positioning is created.
        var pnl = new JPanel(null);
        add(pnl);
        
        var area = new JPanel();
        area.setBackground(Color.white);
        
        res = new Resizable(area);
        res.setBounds(50, 50, 200, 150);
        pnl.add(res);
        
        addMouseListener(new MouseAdapter()
        {
            // If the mouse is pressed on the parent panel, i.e. outside the resizable component, it grabs focus and
            // repaint the component. The rectangles over the border will disappear.
            @Override
            public void mousePressed(MouseEvent me)
            {
                requestFocus();
                res.repaint();
            }
        });
        
        setSize(350, 300);
        setTitle("Resizable component");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new ResizableComponentEx();
            ex.setVisible(true);
        });
    }
    
}
