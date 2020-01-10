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

/**
 * This example uses its own button model instead of a change listener.
 */
public class CustomButtonModelEx extends JFrame
{
    // Data Fields
    private JButton okBtn;
    private JLabel enabledLbl;
    private JLabel pressedLbl;
    private JLabel armedLbl;
    private JCheckBox checkBox;
    
    // Methods
    /**
     * Constructor.
     */
    public CustomButtonModelEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        okBtn = new JButton("OK");
        checkBox = new JCheckBox();
        checkBox.setAction(new CheckBoxAction());
        
        enabledLbl = new JLabel("Enabled: true");
        pressedLbl = new JLabel("Pressed: false");
        armedLbl = new JLabel("Armed: false");
        
        // Set the custom model for the button.
        var model = new OkButtonModel();
        okBtn.setModel(model);
        
        createLayout(okBtn, checkBox, enabledLbl, pressedLbl, armedLbl);
        
        setTitle("Custom button model");
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
                        .addGap(80)
                        .addComponent(arg[1]))
                .addGroup(gl.createSequentialGroup()
                        .addComponent(arg[2])
                        .addComponent(arg[3])
                        .addComponent(arg[4]))
        );
        
        pack();
    }
    
    /**
     * Create a custom button model and override the necessary methods.
     */
    private class OkButtonModel extends DefaultButtonModel
    {
        /**
         * Override the setEnabled() method and add some functionality. The parent method must still be called to
         * proceed with the processing.
         */
        @Override
        public void setEnabled(boolean b)
        {
            if (b)
                enabledLbl.setText("Enabled: true");
            else
                enabledLbl.setText("Enabled: false");
            
            super.setEnabled(b);
        }
        
        @Override
        public void setArmed(boolean b)
        {
            if (b)
                armedLbl.setText("Armed: true");
            else
                armedLbl.setText("Armed: false");
            
            super.setArmed(b);
        }
        
        @Override
        public void setPressed(boolean b)
        {
            if (b)
                pressedLbl.setText("Pressed: true");
            else
                pressedLbl.setText("Pressed: false");
            
            super.setPressed(b);
        }
    }
    
    private class CheckBoxAction extends AbstractAction
    {
        public CheckBoxAction()
        {
            super("Disabed");
        }
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
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
            var ex = new CustomButtonModelEx();
            ex.setVisible(true);
        });
    }
    
}
