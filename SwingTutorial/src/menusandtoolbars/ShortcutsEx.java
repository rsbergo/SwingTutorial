package menusandtoolbars;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * Mnemonics and accelerators are shortcut keys that enable executing commands via keyboard. Mnemonics navigate the menu
 * hierarchy to select a specific menu item whereas accelerators bypass menu hierarchy and directly activate the menu
 * item.
 * 
 * This example utilizes actions, which are objects that can be shared by different components that need the same
 * functionality.
 */
public class ShortcutsEx extends JFrame
{
    /**
     * Constructor.
     */
    public ShortcutsEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        createMenuBar();
        
        setTitle("Mnemonics and accelerators");
        setSize(360, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * Create the menubar.
     */
    private void createMenuBar()
    {
        var menuBar = new JMenuBar();
        
        var iconNew = new ImageIcon("src/Resources/new.png");
        var iconOpen = new ImageIcon("src/Resources/open.png");
        var iconSave = new ImageIcon("src/Resources/save.png");
        var iconExit = new ImageIcon("src/Resources/exit.png");
        
        var fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        
        // These menu items share an action object. Selecting these menu items causes their action commands to be
        // printed to the console.
        var newMenuItem = new JMenuItem(new MenuItemAction("New", iconNew, KeyEvent.VK_N));
        var openMenuItem = new JMenuItem(new MenuItemAction("Open", iconOpen, KeyEvent.VK_O));
        var saveMenuItem = new JMenuItem(new MenuItemAction("Save", iconSave, KeyEvent.VK_S));
        
        // To use a mnemonic, the component must be visible on the screen. First, the menu object must be activated,
        // which makes the Exit menu item visible on the screen. An accelerator is a key shortcut that directly launches
        // a menu item. Mnemonics are visually hinted by underlined characters, the accelerators have their shortcut
        // keys shown next to the menu item's label.
        var exitMenuItem = new JMenuItem("Exit", iconExit);
        exitMenuItem.setMnemonic(KeyEvent.VK_E);
        exitMenuItem.setToolTipText("Exit application");
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_DOWN_MASK));
        exitMenuItem.addActionListener((event) -> System.exit(0));
        
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }
    
    /**
     * An instance of this action is shared by three menu items. Actions use various keys to define their functionality.
     */
    private class MenuItemAction extends AbstractAction
    {
        /**
         * Constructor. Takes a text label, an icon and a mnemonic key as parameters.
         * 
         * The putValue() method associates string values with the specified keys.
         */
        public MenuItemAction(String text, ImageIcon icon, Integer mnemonic)
        {
            super(text);
            putValue(SMALL_ICON, icon);
            putValue(MNEMONIC_KEY, mnemonic);
        }
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println(e.getActionCommand());
        }
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new ShortcutsEx();
            ex.setVisible(true);
        });
    }
}
