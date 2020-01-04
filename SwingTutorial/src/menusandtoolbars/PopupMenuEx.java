package menusandtoolbars;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * A popup menu is another type of menu. It is also called a context menu and usually shown when we right click on a
 * component. The idea is to provide only the commands that are relevant in the current context. Say we have an image.
 * By right clicking on the image, we get a popup window with commands to save, scale, or move the image.
 * 
 * The example shows a popup menu with two commands. The first command maximizes the window, the second quits the
 * application.
 */
public class PopupMenuEx extends JFrame
{
    // Data Fields
    private JPopupMenu popupMenu;
    
    // Methods
    /**
     * Constructor.
     */
    public PopupMenuEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     * 
     * getMenuItemFromPopupMenu() seems to be a bad choice, but it makes it really easy to add a new JMenuItem to
     * popupMenu at any position without having to change any other code (specifying the index for getItem(), for
     * example). It seems that it would be better to have my own class extending JPopupMenu that keeps track of the
     * position of every menu item that has been added.
     */
    private void initUI()
    {
        createPopupMenu();
        
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent e)
            {
                if (getExtendedState() != JFrame.MAXIMIZED_BOTH)
                    getMenuItemFromPopupMenu(popupMenu, "Maximize").setEnabled(true);
                
                if (e.getButton() == MouseEvent.BUTTON3)
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });
        
        setTitle("JPopupMenu");
        setSize(300, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * Create popup menu.
     * 
     * A popup menu consists of JMenuItems. The getExtendedState() method determines the state of the frame. The
     * available states are NORMAL, ICONIFIED, MAXIMIZED_HORIZ, MAXIMIZED_VERT, and MAXIMIZED_BOTH. Once the frame is
     * maximized, the menu item is disabled with setEnabled() method.
     * 
     * The popup menu is shown where we clicked with the mouse button. The getButton() returns which, if any, of the
     * mouse buttons has changed state. MouseEvent.BUTTON3 enables the popup menu only for the right mouse clicks. The
     * maximize menu item is enabled once the window is not maximized.
     */
    private void createPopupMenu()
    {
        popupMenu = new JPopupMenu();
        popupMenu.add(createMaximizeMenuItem());
        popupMenu.add(createQuitMenuItem());
    }
    
    /**
     * Create the maximize popup menu item. When clicked, it maximizes the frame (if not already maximized) and is
     * disabled.
     * 
     * Having a method allows me to use it on both the popup menu as well as on a menubar.
     */
    private JMenuItem createMaximizeMenuItem()
    {
        JMenuItem maximizeMenuItem = new JMenuItem("Maximize");
        maximizeMenuItem.setName("Maximize");
        maximizeMenuItem.addActionListener((e) ->
        {
            if (getExtendedState() != JFrame.MAXIMIZED_BOTH)
            {
                setExtendedState(JFrame.MAXIMIZED_BOTH);
                maximizeMenuItem.setEnabled(false);
            }
        });
        return maximizeMenuItem;
    }
    
    /**
     * Create the quit menu item. When clicked, it closes the application.
     */
    private JMenuItem createQuitMenuItem()
    {
        JMenuItem quitMenuItem = new JMenuItem("Quit");
        quitMenuItem.setName("Quit");
        quitMenuItem.addActionListener((e) -> System.exit(0));
        return quitMenuItem;
    }
    
    /**
     * Find and get the JMenuItem identified by label.
     */
    private JMenuItem getMenuItemFromPopupMenu(JPopupMenu popupMenu, String label)
    {
        for (Component comp : popupMenu.getComponents())
        {
            if (comp instanceof JMenuItem)
            {
                JMenuItem currentComp = (JMenuItem) comp;
                if (label.equals(currentComp.getName()))
                    return currentComp;
            }
        }
        return null;
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new PopupMenuEx();
            ex.setVisible(true);
        });
    }
    
}
