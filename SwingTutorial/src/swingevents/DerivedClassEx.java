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
 * This example derives a class from a component and implements an action listener inside the class. A derived MyButton
 * class is created, which implements the action listener.
 */
public class DerivedClassEx extends JFrame
{
    /**
     * Constructor.
     */
    public DerivedClassEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        // Create the custom MyButton class.
        var closeBtn = new MyButton("Close");
        
        createLayout(closeBtn);
        
        setTitle("Derived class");
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
     * The MyButton class is extended from the JButton class. It implements the ActionListener interface. This way, the
     * event handling is managed within the MyButton class.
     */
    private class MyButton extends JButton implements ActionListener
    {
        /**
         * Constructor
         */
        public MyButton(String text)
        {
            super.setText(text);
            addActionListener(this);
        }
        
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
            var ex = new DerivedClassEx();
            ex.setVisible(true);
        });
    }
    
}
