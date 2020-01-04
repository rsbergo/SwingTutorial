package menusandtoolbars;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Menus can also have submenus, similar commands can be put into groups. Within a menu, commands can be separated with
 * a separator. The separator is a simple line. In addition to mnemonics, menu commands can be launched via
 * accelerators.
 * 
 * This example creates a submenu and separates the groups of menu items with a menu separator.
 */
public class SubmenuEx extends JFrame
{
    /**
     * Constructor.
     */
    public SubmenuEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        createMenuBar();
        
        setTitle("Submenu");
        setSize(360, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * Create the menubar.
     * 
     * A submenu is just like any other menu. It is created the same way. Just add a menu to existing menu.
     * 
     * A separator is a visual line that visually separates menu items. It can be used to group items into some logical
     * places.
     */
    private void createMenuBar()
    {
        // Icons to be used
        var iconNew = new ImageIcon("src/Resources/new.png");
        var iconOpen = new ImageIcon("src/Resources/open.png");
        var iconSave = new ImageIcon("src/Resources/save.png");
        var iconExit = new ImageIcon("src/Resources/exit.png");
        
        // Import menu subitems
        var newsMenuItem = new JMenuItem("Import newsfeed list...");
        var bookmarksMenuItem = new JMenuItem("Import bookmarks...");
        var importMailMenuItem = new JMenuItem("Import mail...");
        
        // Import menu
        var impMenu = new JMenu("Import");
        impMenu.add(newsMenuItem);
        impMenu.add(bookmarksMenuItem);
        impMenu.add(importMailMenuItem);
        
        // File menu subitems
        var newMenuItem = new JMenuItem("New", iconNew);
        var openMenuItem = new JMenuItem("Open", iconOpen);
        var saveMenuItem = new JMenuItem("Save", iconSave);
        
        // Exit menu item
        var exitMenuItem = new JMenuItem("Exit", iconExit);
        exitMenuItem.setToolTipText("Exit application");
        exitMenuItem.addActionListener((event) -> System.exit(0));
        
        // File menu
        var fileMenu = new JMenu("File");
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(impMenu); // add Import menu as an item of the File menu
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        
        // Menubar
        var menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new SubmenuEx();
            ex.setVisible(true);
        });
    }
    
}
