package painting;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The drawing is done on a custom drawing panel, which is a JPanel component. The drawing panel is later added to a
 * JFrame component.
 * 
 * Adding borders to the JPanel with setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)) creates an interesting
 * "animated" image.
 */
class DrawPanel extends JPanel
{
    private void doDrawing(Graphics g)
    {
        // Painting in Swing is done on the Graphics2D object.
        var g2d = (Graphics2D) g;
        
        // The points are painted in blue color.
        g2d.setColor(Color.blue);
        
        for (int i = 0; i <= 1000; i++)
        {
            // The size of the window includes borders and titlebar. Painting is not done there.
            var size = getSize();
            var insets = getInsets();
            
            // Calculate the area where the points will be effectively painted.
            int w = size.width - insets.left - insets.right;
            int h = size.height - insets.top - insets.bottom;
            
            // Get a random number in range of the size of area that was computed.
            var r = new Random();
            int x = Math.abs(r.nextInt()) % w;
            int y = Math.abs(r.nextInt()) % h;
            
            // Draw the point using a drawLine() method specifying the same point twice.
            g2d.drawLine(x, y, x, y);
        }
    }
    
    /**
     * Custom painting is performed inside the paintContainer() method. The super.paingComponent() method calls the
     * method of the parent class, which does some necessary work to prepare the component for drawing. Actual drawing
     * is delegated to the doDrawing() method.
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        doDrawing(g);
    }
}

/**
 * The most simple graphics primitive is point: it is a single dot on the window. There is no method to draw a point in
 * Swing, instead the drawLine() method is used receiving the same point twice.
 * 
 * One point is difficult to observe, therefore this example randomly draws one thousand points on the panel surface.
 */
public class PointsEx extends JFrame
{
    public PointsEx()
    {
        initUI();
    }
    
    private void initUI()
    {
        var drawPanel = new DrawPanel();
        add(drawPanel);
        
        setSize(350, 250);
        setTitle("Points");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new PointsEx();
            ex.setVisible(true);
        });
    }
    
}
