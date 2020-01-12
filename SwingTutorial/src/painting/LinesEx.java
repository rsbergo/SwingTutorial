package painting;

import java.awt.BasicStroke;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

class DrawPanelLines extends JPanel
{
    private void doDrawing(Graphics g)
    {
        var g2d = (Graphics2D) g;
        
        // Create a dash that is used in the stroke object.
        float[] dash1 = { 2f, 0f, 2f };
        float[] dash2 = { 1f, 1f, 1f };
        float[] dash3 = { 4f, 0f, 2f };
        float[] dash4 = { 4f, 4f, 1f };
        
        g2d.drawLine(20, 40, 250, 40);
        
        // Create a stroke. The stroke defines the line width, end caps, line joins, miter limit, dash, and the dash
        // phase.
        var bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);
        var bs2 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash2, 2f);
        var bs3 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash3, 2f);
        var bs4 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash4, 2f);
        
        g2d.setStroke(bs1);
        g2d.drawLine(20, 80, 250, 80);
        
        g2d.setStroke(bs2);
        g2d.drawLine(20, 120, 250, 120);
        
        g2d.setStroke(bs3);
        g2d.drawLine(20, 160, 250, 160);
        
        g2d.setStroke(bs4);
        g2d.drawLine(20, 200, 250, 200);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        doDrawing(g);
    }
}

/**
 * A line is a simple graphics primitive. It is drawn using two points.
 * 
 * This example draws five lines. The first line is drawn using the default values. Other have a different stroke. The
 * stroke is created using the BasicStroke class. It defines a basic set of rendering attributes for the outlines of
 * graphics primitives.
 */
public class LinesEx extends JFrame
{
    /**
     * Constructor.
     */
    public LinesEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        var drawingPanel = new DrawPanelLines();
        add(drawingPanel);
        
        setSize(280, 270);
        setTitle("Lines");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new LinesEx();
            ex.setVisible(true);
        });
    }
    
}
