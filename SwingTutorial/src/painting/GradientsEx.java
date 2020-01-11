package painting;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

class DrawPaneGradients extends JPanel
{
    private void doDrawing(Graphics g)
    {
        var g2d = (Graphics2D) g;
        
        var gp1 = new GradientPaint(5, 5, Color.red, 20, 20, Color.black, true);
        g2d.setPaint(gp1);
        g2d.fillRect(20, 20, 300, 40);
        
        var gp2 = new GradientPaint(5, 25, Color.yellow, 20, 2, Color.black, true);
        g2d.setPaint(gp2);
        g2d.fillRect(20, 80, 300, 40);
        
        var gp3 = new GradientPaint(5, 25, Color.green, 2, 2, Color.black, true);
        g2d.setPaint(gp3);
        g2d.fillRect(20, 140, 300, 40);
        
        // To work with gradients, use Java Swing's GradientPaint class. By manipulating the color values and the
        // starting and ending points, different types of gradients can be obtained. The gradient is activated calling
        // the setPaint() method.
        var gp4 = new GradientPaint(25, 25, Color.blue, 15, 25, Color.black, true);
        g2d.setPaint(gp4);
        g2d.fillRect(20, 200, 300, 40);
        
        var gp5 = new GradientPaint(0, 0, Color.orange, 0, 20, Color.black, true);
        g2d.setPaint(gp5);
        g2d.fillRect(20, 260, 300, 40);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        doDrawing(g);
    }
}

/**
 * In computer graphics, gradient is a smooth blending of shades from light to dark or from one color to another. In 2D
 * drawing programs and paint programs, gradients are used to create colorful backgrounds and special effects, as well
 * as to simulate lights and shadows.
 * 
 * This example presents five rectangles with gradients.
 */
public class GradientsEx extends JFrame
{
    /**
     * Constructor.
     */
    public GradientsEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        var drawPanel = new DrawPaneGradients();
        add(drawPanel);
        
        setSize(350, 350);
        setTitle("Gradients");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new GradientsEx();
            ex.setVisible(true);
        });
    }
    
}
