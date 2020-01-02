package firstprograms;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Display an icon on a frame. It is shown in the left part of the titlebar.
 */
public class FrameIconEx extends JFrame
{
    /**
     * Constructor.
     */
    public FrameIconEx()
    {
        initUI();
    }
    
    /**
     * Initializes the UI.
     * 
     * ImageIcon is used to create the icon. web.png is a small, 22 x 22 px image file.
     */
    private void initUI()
    {
        var webIcon = new ImageIcon("src/resources/web.png");
        
        // The setIconImage() sets the image to be displayed as the icon for this window. the getImage() returns the
        // icon's Image.
        setIconImage(webIcon.getImage());
        
        setTitle("Icon");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new FrameIconEx();
            ex.setVisible(true);
        });
    }
    
}
