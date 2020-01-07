package swingevents;

import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * A listener can be plugged into several sources.
 * 
 * This example shows a listener plugged into several sources. Four buttons and a statusbar are created. The statusbar
 * displays a message upon clicking on the button.
 */
public class MultipleSourcesEx extends JFrame
{
    // Data Fields
    private JLabel statusBar;
    
    // Methods
    /**
     * Constructor.
     */
    public MultipleSourcesEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        statusBar = new JLabel("Ready");
        statusBar.setBorder(BorderFactory.createEtchedBorder());
        
        var butListener = new ButtonListener();
        
        // Each button register the same ButtonListener object.
        var closeBtn = new JButton("Close");
        closeBtn.addActionListener(butListener);
        
        var openBtn = new JButton("Open");
        openBtn.addActionListener(butListener);
        
        var findBtn = new JButton("Find");
        findBtn.addActionListener(butListener);
        
        var saveBtn = new JButton("Save");
        saveBtn.addActionListener(butListener);
        
        createLayout(closeBtn, openBtn, findBtn, saveBtn, statusBar);
        
        setTitle("Multiple Sources");
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
                .addComponent(arg[0])
                .addComponent(arg[1])
                .addComponent(arg[2])
                .addComponent(arg[3])
                .addComponent(arg[4], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(250)
        );
        
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
                .addComponent(arg[1])
                .addComponent(arg[2])
                .addComponent(arg[3])
                .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(arg[4])
        );
        
        gl.linkSize(arg[0], arg[1], arg[2], arg[3]);
        
        pack();
    }
    
    /**
     * ButtonListener inner class.
     * 
     * Determine which button was pressed and create a message for the statusbar. The getSource() method return the
     * object on which the Event initially occurred. The message is set with the setText() method.
     */
    private class ButtonListener implements ActionListener
    {
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            var o = (JButton) e.getSource();
            var label = o.getText();
            
            statusBar.setText("" + label + " button clicked");
        }
        
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new MultipleSourcesEx();
            ex.setVisible(true);
        });
    }
    
}
