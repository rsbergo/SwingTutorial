package swingevents;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * It is possible to remove the registered listeners with the removeActionListener() method.
 * 
 * There are three components on the panel: a button, a check box, and a label. By toggling the check box, a listener is
 * added or removed from the button.
 */
public class RemoveListenerEx extends JFrame
{
    // Data Fields
    private JLabel lbl;
    private JButton addBtn;
    private JCheckBox activeBox;
    private ButtonListener buttonListener;
    private int count = 0;
    
    // Methods
    /**
     * Constructor.
     */
    public RemoveListenerEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        addBtn = new JButton("+");
        
        // A non-anonymous listener must be created if it is to be removed later.
        buttonListener = new ButtonListener();
        
        // Determine whether the check box is selected, then add or remove the listener.
        activeBox = new JCheckBox("Active listener");
        activeBox.addItemListener((ItemEvent event) -> 
        {
            if (activeBox.isSelected())
                addBtn.addActionListener(buttonListener);
            else
                addBtn.removeActionListener(buttonListener);
        });
        
        lbl = new JLabel("0");
        
        createLayout(addBtn, activeBox, lbl);
        
        setTitle("Remove listener");
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
                .addGroup(gl.createParallelGroup()
                        .addComponent(arg[0])
                        .addComponent(arg[2]))
                .addGap(30)
                .addComponent(arg[1])
        );
        
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup()
                        .addComponent(arg[0])
                        .addComponent(arg[1]))
                .addGap(30)
                .addComponent(arg[2])
        );
        
        pack();
    }
    
    /**
     * ButtonListener class.
     */
    private class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            lbl.setText(Integer.toString(++count));
        }
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> 
        {
            var ex = new RemoveListenerEx();
            ex.setVisible(true);
        });
    }
    
}
