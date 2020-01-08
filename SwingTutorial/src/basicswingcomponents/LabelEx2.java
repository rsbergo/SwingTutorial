package basicswingcomponents;

import java.awt.EventQueue;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * JLabel can be used to display images.
 * 
 * In this example, the JLabel component is used to display four icons.
 */
public class LabelEx2 extends JFrame
{
    /**
     * Constructor.
     */
    public LabelEx2()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        // JLabel takes an ImageIcon as a parameter. An icon is a fixed-sized image. ImageIcon paints an icon from a
        // GIF, JPEG, or PNG image.
        var lbl1 = new JLabel(new ImageIcon("src/Resources/basicswingcomponents/cpu.png"));
        var lbl2 = new JLabel(new ImageIcon("src/Resources/basicswingcomponents/drive.png"));
        var lbl3 = new JLabel(new ImageIcon("src/Resources/basicswingcomponents/laptop.png"));
        var lbl4 = new JLabel(new ImageIcon("src/Resources/basicswingcomponents/player.png"));
        
        createLayout(lbl1, lbl2, lbl3, lbl4);
        
        setTitle("Icons");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * Create the layout.
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
                .addComponent(arg[3])
        );
        
        gl.setVerticalGroup(gl.createParallelGroup()
                .addComponent(arg[0])
                .addComponent(arg[1])
                .addComponent(arg[2])
                .addComponent(arg[3])
        );
        
        pack();
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new LabelEx2();
            ex.setVisible(true);
        });
    }
    
}
