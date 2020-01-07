package swingevents;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * There are several ways event handler can be implemented in Java Swing: anonymous inner class, inner class, derived
 * class.
 * 
 * This example shows event handling using an inner ActionListener class. There is a Close button on the pane. Its
 * listener is defined inside a named inner class.
 */
public class InnerClassEx extends JFrame
{
    /**
     * Constructor.
     */
    public InnerClassEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        var closeBtn = new JButton("Close");
        
        // Non-anonymous inner class.
        var listener = new ButtonCloseListener();
        closeBtn.addActionListener(listener);
        
        createLayout(closeBtn);
        
        setTitle("Inner class example");
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
        
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
                .addGap(220)
        );
        
        gl.setVerticalGroup(gl.createParallelGroup()
                .addComponent(arg[0])
                .addGap(220)
        );
        
        pack();
    }
    
    /**
     * ActionListener inner class. The button listener is defined here.
     */
    private class ButtonCloseListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> 
        {
            var ex = new InnerClassEx();
            ex.setVisible(true);
        });
    }
    
}
