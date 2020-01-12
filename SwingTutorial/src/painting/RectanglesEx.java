package painting;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

class DrawPanelRectangles extends JPanel
{
    private void doDrawing(Graphics g)
    {
        var g2d = (Graphics2D) g;
        
        // Set the color of the outline of the rectangle to a soft gray color, so that it does not interfere with the
        // fill color. To draw the outline of the rectangle, use the drawRect() method. The first two parameters are the
        // x and y values; the third and fourth are width and height.
        g2d.setColor(new Color(212, 212, 212));
        g2d.drawRect(10, 15, 90, 60);
        g2d.drawRect(130, 15, 90, 60);
        g2d.drawRect(250, 15, 90, 60);
        g2d.drawRect(10, 105, 90, 60);
        g2d.drawRect(130, 105, 90, 60);
        g2d.drawRect(250, 105, 90, 60);
        g2d.drawRect(10, 195, 90, 60);
        g2d.drawRect(130, 195, 90, 60);
        g2d.drawRect(250, 195, 90, 60);
        
        // To fill the rectangle with a color, use the fillRect() method.
        g2d.setColor(new Color(125, 167, 116));
        g2d.fillRect(10, 15, 90, 60);
        
        g2d.setColor(new Color(42, 179, 231));
        g2d.fillRect(130, 15, 90, 60);
        
        g2d.setColor(new Color(70, 67, 123));
        g2d.fillRect(250, 15, 90, 60);
        
        g2d.setColor(new Color(130, 100, 84));
        g2d.fillRect(10, 105, 90, 60);
        
        g2d.setColor(new Color(252, 211, 61));
        g2d.fillRect(130, 105, 90, 60);
        
        g2d.setColor(new Color(241, 98, 69));
        g2d.fillRect(250, 105, 90, 60);
        
        g2d.setColor(new Color(217, 146, 54));
        g2d.fillRect(10, 195, 90, 60);
        
        g2d.setColor(new Color(63, 121, 185));
        g2d.fillRect(130, 195, 90, 60);
        
        g2d.setColor(new Color(31, 21, 1));
        g2d.fillRect(250, 195, 90, 60);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        doDrawing(g);
    }
}

/**
 * To draw rectangles, use the drawRect() method. To fill rectangles with the current color, use the fillRect() method.
 * 
 * This example draws nine colored rectangles.
 */
public class RectanglesEx extends JFrame
{
    /**
     * Constructor.
     */
    public RectanglesEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        var drawPanel = new DrawPanelRectangles();
        add(drawPanel);
        
        setSize(360, 300);
        setTitle("Rectangles");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new RectanglesEx();
            ex.setVisible(true);
        });
    }
    
}
