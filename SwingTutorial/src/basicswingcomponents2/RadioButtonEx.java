package basicswingcomponents2;

import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * JRadioButton allows the user to select a single exclusive choice from a group of options. It is used with the
 * ButtonGroup component.
 * 
 * This example has three radio buttons; the value of the selected ratio button is shown in a statusbar.
 */
public class RadioButtonEx extends JFrame implements ItemListener
{
    // Data Fields
    private JLabel sbar;
    
    // Methods
    /**
     * Constructor.
     */
    public RadioButtonEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        var lbl = new JLabel("Difficulty");
        
        var group = new ButtonGroup();
        
        // Three JRadioButtons are created and placed into the ButtonGroup. The first radio button is preselected.
        var rb1 = new JRadioButton("Easy", true);
        var rb2 = new JRadioButton("Medium");
        var rb3 = new JRadioButton("Hard");
        
        group.add(rb1);
        group.add(rb2);
        group.add(rb3);
        
        sbar = new JLabel("Selected: Easy");
        
        // All three radio buttons share on ItemListener.
        rb1.addItemListener(this);
        rb2.addItemListener(this);
        rb3.addItemListener(this);
        
        createLayout(lbl, rb1, rb2, rb3, sbar);
        
        setSize(350, 250);
        setTitle("Radio buttons");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * When a radio button is selected, two events are actually triggered: one for selection and one for deselection.
     * The selection is the event of interest.
     */
    @Override
    public void itemStateChanged(ItemEvent e)
    {
        int sel = e.getStateChange();
        
        if (sel == ItemEvent.SELECTED)
        {
            // The source of the event is retrieved with the getSource() method. The text label of the radio button is
            // also retrieved.
            var button = (JRadioButton) e.getSource();
            var text = button.getText();
            
            // Build the string and set it to the label.
            var sb = new StringBuilder("Selected: ");
            sb.append(text);
            
            sbar.setText(sb.toString());
        }
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
        
        gl.setHorizontalGroup(gl.createParallelGroup()
                .addComponent(arg[0])
                .addComponent(arg[1])
                .addComponent(arg[2])
                .addComponent(arg[3])
                .addComponent(arg[4])
        );
        
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(arg[1])
                .addComponent(arg[2])
                .addComponent(arg[3])
                .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(arg[4])
        );
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new RadioButtonEx();
            ex.setVisible(true);
        });
    }
    
}
