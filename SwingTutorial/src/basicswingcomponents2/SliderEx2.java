package basicswingcomponents2;

import java.awt.EventQueue;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;

/**
 * This example creates a volume control with a JSlider. A JSlider and a JLabel component are show. By dragging the
 * slider, the icon on the label component is changed.
 */
public class SliderEx2 extends JFrame
{
    // Data Fields
    private JSlider slider;
    private JLabel lbl;
    private ImageIcon mute;
    private ImageIcon min;
    private ImageIcon med;
    private ImageIcon max;
    
    // Methods
    /**
     * Constructor.
     */
    public SliderEx2()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        loadImages();
        
        // JSlider constructor. The constructor parameters are minimum value, maximum value, and current value.
        slider = new JSlider(0, 150, 0);
        
        // Add a ChangeListener to the slider. Inside the listener, determine the current slider value and update the
        // label accordingly.
        slider.addChangeListener((ChangeEvent event) -> 
        {
            int value = slider.getValue();
            
            if (value == 0)
                lbl.setIcon(mute);
            else if (value > 0 && value <= 30)
                lbl.setIcon(min);
            else if (value > 30 && value <= 80)
                lbl.setIcon(med);
            else
                lbl.setIcon(max);
        });
        
        lbl = new JLabel(mute, JLabel.CENTER);
        
        createLayout(slider, lbl);
        
        setTitle("JSlider");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    /**
     * Load the images used for icons.
     * 
     * Load the image files from the disk.
     */
    private void loadImages()
    {
        mute = new ImageIcon("src/Resources/basicswingcomponents2/mute.png");
        min = new ImageIcon("src/Resources/basicswingcomponents2/min.png");
        med = new ImageIcon("src/Resources/basicswingcomponents2/med.png");
        max = new ImageIcon("src/Resources/basicswingcomponents2/max.png");
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
        );
        
        gl.setVerticalGroup(gl.createParallelGroup()
                .addComponent(arg[0])
                .addComponent(arg[1])
        );
        
        pack();
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> 
        {
            var ex = new SliderEx2();
            ex.setVisible(true);
        });
    }
    
}
