package swingmodels;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.DefaultButtonModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * The model is used for various kinds of buttons, like push buttons, check boxes, radio boxes and for menu items.
 * 
 * This example illustrates the model for a JButton. It is possible only to manage the state of the button, since no
 * data can be associated with a push button.
 * 
 * This example includes a button, a check box, and three labels. The labels represent three properties of the button:
 * pressed, disabled, or armed state.
 * 
 * Clicking the check box triggers a change in the state of the button. The DisabledChangeListener detects this change,
 * and its stateChanged is invoked.
 */
public class ButtonModelEx extends JFrame
{
    // Data Fields
    private JButton okBtn;
    private JLabel enableLbl;
    private JLabel pressedLbl;
    private JLabel armedLbl;
    private JCheckBox checkBox;
    
    // Methods
    /**
     * Constructor.
     */
    public ButtonModelEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        okBtn = new JButton("OK");
        
        // A ChangeListener is used to listen for buttons state changes.
        okBtn.addChangeListener(new DisabledChangeListener());
        checkBox = new JCheckBox();
        checkBox.setAction(new CheckBoxAction());
        
        enableLbl = new JLabel("Enabed: true");
        pressedLbl = new JLabel("Pressed: false");
        armedLbl = new JLabel("Armed: false");
        
        createLayout(okBtn, checkBox, enableLbl, pressedLbl, armedLbl);
        
        setTitle("ButtonModel");
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
        
        gl.setHorizontalGroup(gl.createParallelGroup()
                .addGroup(gl.createSequentialGroup()
                        .addComponent(arg[0])
                        .addGap(80)
                        .addComponent(arg[1]))
                .addGroup(gl.createParallelGroup()
                        .addComponent(arg[2])
                        .addComponent(arg[3])
                        .addComponent(arg[4]))
        );
        
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup()
                        .addComponent(arg[0])
                        .addComponent(arg[1]))
                .addGap(40)
                .addGroup(gl.createSequentialGroup()
                        .addComponent(arg[2])
                        .addComponent(arg[3])
                        .addComponent(arg[4]))
        );
        
        pack();
    }
    
    private class DisabledChangeListener implements ChangeListener
    {

        @Override
        public void stateChanged(ChangeEvent e)
        {
            // Get the default button model.
            var model = (DefaultButtonModel) okBtn.getModel();
            
            // Query the model whether the button is enabled. The label is updated accordingly.
            if (model.isEnabled())
                enableLbl.setText("Enabled: true");
            else
                enableLbl.setText("Enabled: false");
            
            if (model.isArmed())
                armedLbl.setText("Armed: true");
            else
                armedLbl.setText("Armed: false");
            
            if (model.isPressed())
                pressedLbl.setText("Pressed: true");
            else
                pressedLbl.setText("Pressed: false");
        }
        
    }
    
    private class CheckBoxAction extends AbstractAction
    {
        public CheckBoxAction()
        {
            super("Disabled");
        }
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // The check box enables or disabled the button. To enable the OK button, call the setEnabled() method. The
            // state of the button is changed. Internally, the Swing toolkit works with a model The setEnabled() is
            // another convenience method for programmers.
            if (okBtn.isEnabled())
                okBtn.setEnabled(false);
            else
                okBtn.setEnabled(true);
        }
        
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> 
        {
            var ex = new ButtonModelEx();
            ex.setVisible(true);
        });
    }
    
}
