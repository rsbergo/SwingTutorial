package swingtutorial;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Creates a simple Swing application.
 */
public class SimpleEx extends JFrame
{
    /**
     * Constructor.
     * 
     * It is a good programming practice to not put the application code into constructors, but delegate the task to a
     * specific method.
     */
    public SimpleEx()
    {
        initUI();
    }
    
    /**
     * Initializes the JFrame component.
     * 
     * JFrame is a top-level container. The basic purpose of containers is to hold components of the application.
     */
    private void initUI()
    {
        setTitle("Simple example");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    // Driver
    public static void main(String[] args)
    {
        // The invokeLater() method places the application on the Swing Event Queue. It is used to ensure that all UI
        // updates are concurrency-safe.
        EventQueue.invokeLater(() ->
        {
            // var allows variable type inference; the type for the local variable will be inferred by the compiler.
            var ex = new SimpleEx();
            ex.setVisible(true);
        });
    }
    
}
