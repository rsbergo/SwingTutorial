package basicswingcomponents2;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * JComboBox is a component that combines a button or editable field and a drop-down list. The user can select a value
 * from the drop-down list, which appears at the user's request. If the combo box is editable, then the combo box
 * includes an editable field into which the user can type a value.
 * 
 * This example includes a combo box and a label. The combo box contains a list of strings denoting names of Linux
 * distributions. The selected item from the combo box is displayed in the label. The combo box uses its ItemListener to
 * detect changes.
 */
public class ComboBoxEx extends JFrame implements ItemListener
{
    // Data Fields
    private JLabel display;
    private JComboBox<String> box;
    private String[] distros;
    
    // Methods
    /**
     * Constructor.
     */
    public ComboBoxEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        // The JComboBox will hold these string values.
        distros = new String[] { "Ubuntu", "Redhat", "Arch", "Debian", "Mint" };
        
        // The constructor of the JComboBox takes a string array of Linux distributions. A listener is plugged to the
        // object.
        box = new JComboBox<String>(distros);
        box.addItemListener(this);
        
        // The display area is a simple JLabel. It displays the item that is initially shown in the combo box.
        display = new JLabel("Ubuntu");
        
        createLayout(box, display);
        
        setTitle("JComboBox");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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
        );
        
        // Vertically, the two components are aligned to the baseline of their text.
        gl.setVerticalGroup(gl.createParallelGroup(BASELINE)
                .addComponent(arg[0])
                .addComponent(arg[1])
        );
        
        pack();
    }
    
    /**
     * The itemStateChanged() is invoked when an item has been selected or deselected by the user. Check for
     * ItemEvent.SELECTED state and set the combo box's selected item to the label.
     */
    @Override
    public void itemStateChanged(ItemEvent e)
    {
        if (e.getStateChange() == ItemEvent.SELECTED)
            display.setText(e.getItem().toString());
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new ComboBoxEx();
            ex.setVisible(true);
        });
    }
    
}
