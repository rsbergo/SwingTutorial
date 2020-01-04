package menusandtoolbars;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;

/**
 * JRadioButtonMenuItem enabled to select from a mutually exclusive list of options. Selecting a particular
 * JRadioButtonMenuItem deselects all other items. JRadioButtonMenuItems are placed into a ButtonGroup.
 * 
 * This example creates a menu with three JRadioButtonMenuItem components.
 */
public class RadioMenuItemEx extends JFrame
{
    // Data Fields
    private JLabel statusBar;
    
    // Methods
    /**
     * Constructor.
     */
    public RadioMenuItemEx()
    {
        initUI();
    }
    
    /**
     * Initializes the UI.
     */
    private void initUI()
    {
        createStatusBar();
        createMenuBar();
        
        setTitle("JRadioButtonMenuItem");
        setSize(360, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * Create the statusbar.
     */
    private void createStatusBar()
    {
        statusBar = new JLabel("Easy");
        statusBar.setBorder(BorderFactory.createEtchedBorder());
        add(statusBar, BorderLayout.SOUTH);
    }
    
    /**
     * Creates the menubar.
     * 
     * JMenuItems are added to an underlying JPopupMenu, so getComponents must be called from difMenu's getPopupMenu().
     */
    private void createMenuBar()
    {
        JMenu difMenu = createDifficultyMenu();
        createDifficultyButtonGroup(difMenu.getPopupMenu().getComponents());
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(difMenu);
        setJMenuBar(menuBar);
    }
    
    /**
     * Create the easy menu option.
     */
    private JRadioButtonMenuItem createMenuOption(String text, boolean selected)
    {
        JRadioButtonMenuItem rMenuItem = new JRadioButtonMenuItem(text);
        rMenuItem.setSelected(selected);
        
        // The getStateChange() determines the type of state change. If the change is ItemEvent.SELECTED, the status on
        // the statusbar is changed.
        rMenuItem.addItemListener((e) ->
        {
            if (e.getStateChange() == ItemEvent.SELECTED)
                statusBar.setText(text);
        });
        return rMenuItem;
    }
    
    /**
     * Create the difficulty menu.
     */
    private JMenu createDifficultyMenu()
    {
        JMenu difMenu = new JMenu("Difficulty");
        difMenu.setMnemonic(KeyEvent.VK_F);
        
        difMenu.add(createMenuOption("Easy", true));
        difMenu.add(createMenuOption("Medium", false));
        difMenu.add(createMenuOption("Hard", false));
        
        return difMenu;
    }
    
    /**
     * The ButtonGroup is used to create a multiple-exclusion scope for a set of buttons. The JRadioButtonMenuItem must
     * be placed on both the JMenu and the ButtonGroup.
     */
    private void createDifficultyButtonGroup(Component... arg)
    {
        ButtonGroup difGroup = new ButtonGroup();
        for (Component comp : arg)
        {
            if (comp instanceof JRadioButtonMenuItem)
                difGroup.add((JRadioButtonMenuItem) comp);
        }
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new RadioMenuItemEx();
            ex.setVisible(true);
        });
    }
    
}
