package swingtutorial;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.EventQueue;

/**
 * Creates a Swing application with a simple window and a button. Clicking the button terminates the application.
 */
public class QuitButtonEx extends JFrame
{
    /**
     * Constructor.
     */
    public QuitButtonEx()
    {
        initUI();
    }
    
    /**
     * Initializes the UI containing a button.
     * 
     * Position a JButton on the window and add an action listener to this button.
     */
    private void initUI()
    {
        var quitButton = new JButton("Quit");
        
        // Plug an action listener to the button. The action terminates the application.
        quitButton.addActionListener((event) -> System.exit(0));
        
        // The child components need to be placed into containers. This is delegated to createLayout() method.
        createLayout(quitButton);
        
        setTitle("Quit button");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * Creates the layout.
     * 
     * The content pane of a JFrame is an area where child components are placed. The children are organized by special
     * non-visible components called layout managers.
     * 
     * The default layout manager of a content panel is the BorderLayout manager, which is very simple. GroupLayout
     * manager is more powerful and flexible.
     */
    private void createLayout(JComponent... arg)
    {
        var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);
        
        // Creates gaps between components and the edges of the container. Spaces or gaps are important part of the
        // design of each application.
        gl.setAutoCreateContainerGaps(true);
        
        // GroupLayout manager defines the layout for each dimension independently. In one step, components are placed
        // alongside the horizontal axis; in the other step, components are placed alongside the vertical axis.
        // Components can be arranged sequentially or in parallel. In a horizontal layout, a row of components is called
        // a sequential group and a column of components is called a parallel group. In a vertical layout, a column of
        // components is called a sequential group and a row of components is called a parallel group.
        gl.setHorizontalGroup(gl.createSequentialGroup().addComponent(arg[0]));
        gl.setVerticalGroup(gl.createSequentialGroup().addComponent(arg[0]));
    }
    
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new QuitButtonEx();
            ex.setVisible(true);
        });
    }
    
}
