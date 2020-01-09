package basicswingcomponents2;

import java.awt.EventQueue;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;

/**
 * JSlider is a component that lets the user graphically select a value by sliding a knob within a bounded interval.
 * Moving the slider's knob, the stateChanged() method of the slider's ChangeListener is called.
 * 
 * JSlider can optionally show tick marks for the range of its values. The tick marks are controlled with the
 * setMinerTickSpacing(), setMajorTickSpacing(), and setPaintTicks() methods.
 * 
 * In this example, a value selected from a slider is displayed in a label component.
 */
public class SliderEx extends JFrame
{
    // Data Fields
    private JSlider slider;
    private JLabel lbl;
    
    // Methods
    /**
     * Constructor.
     */
    public SliderEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        // Create a JSlider with the minimum, maximum, and current values. Set the distance between minor and major tick
        // marks. The setPaintTicks() method determines whether tick marks are painted on the slider.
        slider = new JSlider(0, 100, 0);
        slider.setMinorTickSpacing(5);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        
        // A ChangeEvent is triggered when the slider has changed in some way. The current value of the slider is
        // retrieved with its getValue() method, convert the integer into a string with Integer.toString() and set it to
        // the label with the label's setText().
        slider.addChangeListener((ChangeEvent event) -> 
        {
            int value = slider.getValue();
            lbl.setText(Integer.toString(value));
        });
        
        lbl = new JLabel("...");
        
        createLayout(slider, lbl);
        
        setTitle("JSlider");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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
        
        gl.setHorizontalGroup(gl.createParallelGroup()
                .addComponent(arg[0])
                .addComponent(arg[1])
        );
        
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
                .addComponent(arg[1])
        );
        
        pack();
    }
    
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new SliderEx();
            ex.setVisible(true);
        });
    }
    
}
