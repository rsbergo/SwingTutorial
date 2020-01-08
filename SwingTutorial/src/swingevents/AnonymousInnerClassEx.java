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
 * This example shows event handling using an anonymous inner class. In this example, there is a button that closes the
 * window upon clicking.
 */
public class AnonymousInnerClassEx extends JFrame
{
    /**
     * Constructor.
     */
    public AnonymousInnerClassEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        // The Close button is the event source. It will generate events.
        var closeBtn = new JButton("Close");
        
        // An action listener is registered with the button. The events are sent to the event target. The event target
        // in this case is ActionListener class; in this code, anonymous inner class is used.
        closeBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                System.exit(0);
            }
        });
        
        createLayout(closeBtn);
        
        setTitle("Anonymous inner class");
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
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> 
        {
            var ex = new AnonymousInnerClassEx();
            ex.setVisible(true);
        });
    }
    
}
