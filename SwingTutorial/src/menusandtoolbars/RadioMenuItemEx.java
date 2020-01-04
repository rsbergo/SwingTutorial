package menusandtoolbars;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
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
        createMenuBar();
        
        statusBar = new JLabel("Easy");
        statusBar.setBorder(BorderFactory.createEtchedBorder());
        add(statusBar, BorderLayout.SOUTH);
        
        setTitle("JRadioButtonMenuItem");
        setSize(360, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * Creates the menubar.
     */
    private void createMenuBar()
    {
        var menuBar = new JMenuBar();
        var difMenu = new JMenu("Difficulty");
        difMenu.setMnemonic(KeyEvent.VK_F);
        
        // The ButtonGroup is used to create a multiple-exclusion scope for a set of buttons. The JRadioButtonMenuItem
        // must be placed on both the JMenu and the ButtonGroup.
        var difGroup = new ButtonGroup();
        
        var easyRMenuItem = new JRadioButtonMenuItem("Easy");
        easyRMenuItem.setSelected(true);
        difMenu.add(easyRMenuItem);
        
        // The getStateChange() determines the type of state change. If the change is ItemEvent.SELECTED, the status on
        // the statusbar is changed.
        easyRMenuItem.addItemListener((e) ->
        {
            if (e.getStateChange() == ItemEvent.SELECTED)
                statusBar.setText("Easy");
        });
        
        var mediumRMenuItem = new JRadioButtonMenuItem("Medium");
        difMenu.add(mediumRMenuItem);
        
        mediumRMenuItem.addItemListener((e) ->
        {
            if (e.getStateChange() == ItemEvent.SELECTED)
                statusBar.setText("Medium");
        });
        
        var hardRMenuItem = new JRadioButtonMenuItem("Hard");
        difMenu.add(hardRMenuItem);
        
        hardRMenuItem.addItemListener((e) ->
        {
            if (e.getStateChange() == ItemEvent.SELECTED)
                statusBar.setText("Hard");
        });
        
        difGroup.add(easyRMenuItem);
        difGroup.add(mediumRMenuItem);
        difGroup.add(hardRMenuItem);
        
        menuBar.add(difMenu);
        setJMenuBar(menuBar);
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
