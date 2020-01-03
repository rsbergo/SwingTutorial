package menusandtoolbars;

import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * A menu is a group of commands located in a menubar. A toolbar has buttons with some common commands in the
 * application.
 * 
 * Shows a menu with one item. Selecting the Exit menu item closes the application.
 */
public class SimpleMenuEx extends JFrame
{
    /**
     * Constructor.
     */
    public SimpleMenuEx()
    {
        initUI();
    }
    
    /**
     * Initializes the UI.
     */
    private void initUI()
    {
        createMenuBar();
        
        setTitle("Simple menu");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * Creates the menubar.
     */
    private void createMenuBar()
    {
        // A menubar is created with JMenuBar.
        var menuBar = new JMenuBar();
        
        // An Exit icon is displayed in the menu.
        var exitIcon = new ImageIcon("scr/Resources/exit.png");
        
        // A menu object is created with JMenu and a mnemonic is bound to it.
        var fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        
        // A menu object consists of menu items. A menu item is created with JMenuItem. A menu item has its own
        // mnemonic. JMenuItem is a special kind of a button component and can have an action listener added to it.
        var eMenuItem = new JMenuItem("Exit", exitIcon);
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText("Exit application");
        eMenuItem.addActionListener((event) -> System.exit(0));
        
        // The menu item is added to the menu object and the menu object is inserted into the menu bar.
        fileMenu.add(eMenuItem);
        menuBar.add(fileMenu);
        
        // setJMenuBar() method sets the menubar for the JFrame container.
        setJMenuBar(menuBar);
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new SimpleMenuEx();
            ex.setVisible(true);
        });
    }
    
}
