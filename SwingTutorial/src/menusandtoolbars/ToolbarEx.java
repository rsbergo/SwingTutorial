package menusandtoolbars;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;

/**
 * Menu group commands that can be used in an application. Toolbars provide a quick access to the most frequently used
 * commands. In Java Swing, the JToolBar class creates a toolbar in an application.
 * 
 * This example creates a toolbar with one exit button.
 */
public class ToolbarEx extends JFrame
{
    /**
     * Constructor.
     */
    public ToolbarEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        createMenuBar();
        createToolBar();
        
        setTitle("Simple toolbar");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * Create the menubar.
     */
    private void createMenuBar()
    {
        var menuBar = new JMenuBar();
        var fileMenu = new JMenu("File");
        
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }
    
    /**
     * Create the toolbar.
     * 
     * A toolbar is created with JToolBar. A button inserted into a toolbar is a regular JButton. The toolbar is placed
     * to the north area of the BorderLayout. BorderLayout is the default layout manager for the content pane of a
     * JFrame, JWindow, JDialog, JInternalFrame, and Applet.
     */
    private void createToolBar()
    {
        var toolBar = new JToolBar();
        var icon = new ImageIcon("src/Resources/exit2.png");
        
        var exitButton = new JButton(icon);
        toolBar.add(exitButton);
        
        exitButton.addActionListener((e) -> System.exit(0));
        add(toolBar, BorderLayout.NORTH);
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new ToolbarEx();
            ex.setVisible(true);
        });
    }
    
}
