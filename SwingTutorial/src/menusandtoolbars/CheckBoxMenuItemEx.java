package menusandtoolbars;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.ItemSelectable;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 * A JCheckBoxMenuItem is a meny item that can be selected or deselected. If selected, the menu item typically appears
 * with a checkmark next to it. If unselected or deselected, the menu item appears without a checkmark. Like a regular
 * menu item, a check box menu item can have either a text or a graphic icon associated with it, or both.
 * 
 * This example uses a JCheckBoxMenuItem to toggle the visibility of a statusbar. The statusbar is a simple JLabel
 * component with a raised EtchedBorder around it so it is visible.
 */
public class CheckBoxMenuItemEx extends JFrame
{
    // Data Fields
    private JLabel statusBar;
    
    // Methods
    /**
     * Constructor.
     */
    public CheckBoxMenuItemEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        createStatusBar();
        createMenuBar();
        
        setTitle("JCheckBoxMenuItem");
        setSize(360, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * Creates the statusbar.
     */
    private void createStatusBar()
    {
        statusBar = new JLabel("Ready");
        statusBar.setBorder(BorderFactory.createEtchedBorder());
        add(statusBar, BorderLayout.SOUTH);
    }
    
    /**
     * Create the menubar.
     */
    private void createMenuBar()
    {
        var menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        menuBar.add(createViewMenu());
        setJMenuBar(menuBar);
    }
    
    /**
     * Create the File menu.
     */
    private JMenu createFileMenu()
    {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        
        return fileMenu;
    }
    
    /**
     * Create the View Menu.
     */
    private JMenu createViewMenu()
    {
        JMenu viewMenu = new JMenu("View");
        viewMenu.setMnemonic(KeyEvent.VK_V);
        
        viewMenu.add(createShowStatusBarMenuItem());
        
        return viewMenu;
    }
    
    /**
     * Create the show status bar menu item.
     */
    private JCheckBoxMenuItem createShowStatusBarMenuItem()
    {
        JCheckBoxMenuItem showStatusBarMenuItem = new JCheckBoxMenuItem("Show statusbar");
        showStatusBarMenuItem.setMnemonic(KeyEvent.VK_S);
        
        // Choose which letter is going to be underlined.
        showStatusBarMenuItem.setDisplayedMnemonicIndex(5);
        
        // The statusbar is initially visible.
        showStatusBarMenuItem.setSelected(true);
        
        // JCheckBoxMenuItem is a special kind of a button component. It implements the ItemSelectable interface. An
        // ItemListener can be used to listen to its state changes.
        showStatusBarMenuItem.addItemListener((e) ->
        {
            if (e.getStateChange() == ItemEvent.SELECTED)
                statusBar.setVisible(true);
            else
                statusBar.setVisible(false);
        });
        
        return showStatusBarMenuItem;
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new CheckBoxMenuItemEx();
            ex.setVisible(true);
        });
    }
    
}
