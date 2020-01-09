package basicswingcomponents2;

import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * JCheckBox is a box with a label that has two states: on and off. If the check box is selected, it is represented by a
 * tick in a box. With JCkecBox, it is possible to use an ActionListener or an ItemListener. Usually the ItemListener is
 * used. ItemListener is the interface for receiving item events. The class that is interested in processing an item
 * event, e.g. the observer, implements this interface. The observer object is registered with a component using the
 * component's addItemListener() method. When an item selection event occurs, the observer's itemStateChange() method is
 * invoked.
 * 
 * This example shows or hides the title of the window depending whether the check box is selected.
 * 
 * The application class implements the ItemListener interface. This means that this class has to provide the
 * itemStateChanged() method in which it reacts to the item selection events.
 */
public class CheckBoxEx extends JFrame implements ItemListener
{
    /**
     * Constructor.
     */
    public CheckBoxEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        // Create the JCheckBox. This constructor takes a text and the state of the check box as parameters. The check
        // box is initially selected. The application class is registered to be the observer of the selection events of
        // the check box.
        var cb = new JCheckBox("Show title", true);
        cb.addItemListener(this);
        
        createLayout(cb);
        
        setSize(280, 200);
        setTitle("JCheckBox");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * The ItemEvent's getStateChange() method determines the state of the check box. ItemEvent is a semantic event
     * which indicates that an item was selected or deselected. It is sent to the registered observer. Depending on the
     * state of the check box, the title of the window is shown using the setTitle() method.
     */
    @Override
    public void itemStateChanged(ItemEvent e)
    {
        int sel = e.getStateChange();
        
        if (sel == ItemEvent.SELECTED)
            setTitle("JCheckBox");
        else
            setTitle("");
    }
    
    /**
     * Create the layout.
     * 
     * @param arg
     */
    private void createLayout(JComponent... arg)
    {
        var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);
        
        gl.setAutoCreateContainerGaps(true);
        
        gl.setHorizontalGroup(gl.createParallelGroup()
                .addComponent(arg[0])
        );
        
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new CheckBoxEx();
            ex.setVisible(true);
        });
    }
    
}
