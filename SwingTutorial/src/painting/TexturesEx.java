package painting;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * BufferedImage is a rectangle of pixels stored in memory. It is one of the most important image types in Swing. Many
 * Swing methods return a BufferedImage to work with.
 */
class DrawingPanelTexture extends JPanel
{
    // Data Fields
    private BufferedImage slate;
    private BufferedImage java;
    private BufferedImage pane;
    
    // Methods
    /**
     * Constructor.
     */
    public DrawingPanelTexture()
    {
        loadImages();
    }
    
    /**
     * Load images from files.
     */
    private void loadImages()
    {
        try
        {
            // Read the image into the buffered image using ImageIO.read() method. It takes a File object and returns a
            // BufferedImage.
            slate = ImageIO.read(new File("src/Resources/painting/slate.png"));
            java = ImageIO.read(new File("src/Resources/painting/java.png"));
            pane = ImageIO.read(new File("src/Resources/painting/pane.png"));
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(this, "Could not load images", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
    
    private void doDrawing(Graphics g)
    {
        var g2d = (Graphics2D) g;
        
        // Create a TexturePaint class out of the buffered image.
        var slateTp = new TexturePaint(slate, new Rectangle(0, 0, 90, 60));
        var javaTp = new TexturePaint(java, new Rectangle(0, 0, 90, 60));
        var paneTp = new TexturePaint(pane, new Rectangle(0, 0, 90, 60));
        
        // Fill the rectangle with the texture.
        g2d.setPaint(slateTp);
        g2d.fillRect(10, 15, 90, 60);
        
        g2d.setPaint(javaTp);
        g2d.fillRect(130, 15, 90, 60);
        
        g2d.setPaint(paneTp);
        g2d.fillRect(250, 15, 90, 60);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        doDrawing(g);
    }
}

/**
 * A texture is a bitmap image applied to a shape. To work with textures in Java 2D, use the TexturePaing class.
 * 
 * In this example, three rectangles are filled with three different textures.
 */
public class TexturesEx extends JFrame
{
    /**
     * Constructor.
     */
    public TexturesEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        var drawingPanel = new DrawingPanelTexture();
        add(drawingPanel);
        
        setTitle("Textures");
        setSize(360, 120);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new TexturesEx();
            ex.setVisible(true);
        });
    }
    
}
