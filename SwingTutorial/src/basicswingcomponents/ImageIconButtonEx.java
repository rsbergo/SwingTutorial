package basicswingcomponents;

import java.awt.EventQueue;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * Swing components are basic building blocks of an application. Swing has a wide range of various components, including
 * buttons, check boxes, sliders, and list boxes.
 * 
 * JButton is an implementation of a push button. It is used to trigger an action if the user clicks on it.
 * 
 * JButton can display a text, an icon, or both. Many components can be decorated with icons, using the ImageIcon class.
 * 
 * This example displays three buttons: one displays text, one icon, and one both text and icon.
 */
public class ImageIconButtonEx extends JFrame
{
    /**
     * Constructor.
     */
    public ImageIconButtonEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        var saveIcon = new ImageIcon("src/Resources/basicswingcomponents/save.png");
        var homeIcon = new ImageIcon("src/Resources/basicswingcomponents/home.png");
        
        
        var quitBtn = new JButton("Quit");
        var saveBtn = new JButton(saveIcon);
        var homeBtn = new JButton("Home", homeIcon);
        
        createLayout(quitBtn, saveBtn, homeBtn);
        
        setTitle("JButtons");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * Create the Layout.
     */
    private void createLayout(JComponent... arg)
    {
        var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);
        
        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);
        
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
                .addComponent(arg[1])
                .addComponent(arg[2])
        );
        
        gl.setVerticalGroup(gl.createParallelGroup()
                .addComponent(arg[0])
                .addComponent(arg[1])
                .addComponent(arg[2])
        );
        
        gl.linkSize(arg[0], arg[1], arg[2]);
        
        pack();
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new ImageIconButtonEx();
            ex.setVisible(true);
        });
    }
    
}
