package menusandtoolbars;

import java.awt.EventQueue;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 * Some applications display a menu on the right side. Typically, it is a Help menu.
 * 
 * This example shows three menus on the left and one menu on the right.
 */
public class RightMenuEx extends JFrame
{
    /**
     * Constructor.
     */
    public RightMenuEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        createMenuBar();
        
        setTitle("Right menu");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * Create the menubar.
     * 
     * Box.createHorizontalGlue() adds a horizontal glue to the menubar. A glue absorbs all the extra space available.
     * This will push the Help menu to the right of the menubar.
     */
    private void createMenuBar()
    {
        var menuBar = new JMenuBar();
        
        var fileMenu = new JMenu("File");
        var viewMenu = new JMenu("View");
        var toolsMenu = new JMenu("Tools");
        var helpMenu = new JMenu("Help");
        
        menuBar.add(fileMenu);
        menuBar.add(viewMenu);
        menuBar.add(toolsMenu);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(helpMenu);
        
        setJMenuBar(menuBar);
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new RightMenuEx();
            ex.setVisible(true);
        });
    }
    
}
