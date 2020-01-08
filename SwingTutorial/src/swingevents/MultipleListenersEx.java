package swingevents;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.GroupLayout.PREFERRED_SIZE;
import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Year;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 * It is possible to register several listeners for one event.
 * 
 * In this example, there is a button, a spinner, and a statusbar. Two button listeners are used for one event: one
 * click of a button will add one year to the spinner and update the statusbar. The statusbar will show how many times
 * the button has been clicked.
 */
public class MultipleListenersEx extends JFrame
{
    // Data Fields
    private JLabel statusBar;
    private JSpinner spinner;
    private int count = 0;
    
    // Methods
    /**
     * Constructor.
     */
    public MultipleListenersEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        statusBar = new JLabel("0");
        statusBar.setBorder(BorderFactory.createEtchedBorder());
        
        // Two button listeners are registered.
        JButton addBtn = new JButton("+");
        addBtn.addActionListener(new ButtonListener1());
        addBtn.addActionListener(new ButtonListener2());
        
        int currentYear = Year.now().getValue();
        
        // Create the spinner component. A year model is used for the spinner. The SpinnerNumberModel() arguments are
        // the initial value, min and max values, and the step.
        var yearModel = new SpinnerNumberModel(currentYear, currentYear - 100, currentYear + 100, 1);
        spinner = new JSpinner(yearModel);
        
        // Remove the thousands separator.
        spinner.setEditor(new JSpinner.NumberEditor(spinner, "#"));
        
        createLayout(addBtn, spinner, statusBar);
        
        setTitle("Multiple Listeners");
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
        
        gl.setHorizontalGroup(gl.createParallelGroup()
                .addGroup(gl.createSequentialGroup()
                        .addComponent(arg[0])
                        .addGap(20)
                        .addComponent(arg[1], DEFAULT_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                .addComponent(arg[2], DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(arg[0])
                        .addGap(20)
                        .addComponent(arg[1], DEFAULT_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                .addPreferredGap(RELATED, DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(arg[2])
        );
        
        pack();
    }
    
    /**
     * ButtonListener1 class.
     * 
     * Increases the value of the spinner component.
     */
    private class ButtonListener1 implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            var val = (Integer) spinner.getValue();
            spinner.setValue(++val);
        }
    }
    
    /**
     * ButtonListener2 class.
     * 
     * Increases the value of the status bar.
     */
    private class ButtonListener2 implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            statusBar.setText(Integer.toString((++count)));
        }
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new MultipleListenersEx();
            ex.setVisible(true);
        });
    }
    
}
