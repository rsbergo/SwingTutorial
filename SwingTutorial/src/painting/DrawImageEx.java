package painting;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

class DrawPanelImage extends JPanel
{
    // Data Fields
    private Image myImage;
    
    /**
     * Constructor.
     */
    public DrawPanelImage()
    {
        initPanel();
    }
    
    /**
     * Initialize the draw panel.
     * 
     * Call the loadImage() method, determine the image dimensions and set the preferred size of the panel component.
     * Together with the pack() method, this display the image that will exactly fit the window.
     */
    private void initPanel()
    {
        loadImage();
        var dm = new Dimension(myImage.getWidth(null), myImage.getHeight(null));
        setPreferredSize(dm);
    }
    
    /**
     * Loads an image from the disk using the ImageIcon class. This class simplifies the work with the images in Java
     * Swing.
     */
    private void loadImage()
    {
        myImage = new ImageIcon("src/Resources/painting/icesid.jpg").getImage();
    }
    
    private void doDrawing(Graphics g)
    {
        var g2d = (Graphics2D) g;
        
        // The image is drawn using the drawImage() method.
        g2d.drawImage(myImage, 0, 0, null);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        doDrawing(g);
    }
}

/**
 * An image is an array of pixels, each pixel representing a color at a given position. Components, such as JLabel, can
 * be used to display an image, or the image can be drawn using the Java 2D API.
 * 
 * This example draws an image on the panel. The image fits the JFrame window.
 */
public class DrawImageEx extends JFrame
{
    /**
     * Constructor.
     */
    public DrawImageEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        var drawPanel = new DrawPanelImage();
        add(drawPanel);
        
        setTitle("Image");
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new DrawImageEx();
            ex.setVisible(true);
        });
    }
    
}
