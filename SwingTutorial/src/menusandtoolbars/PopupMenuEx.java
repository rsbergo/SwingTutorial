package menusandtoolbars;

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
     */
    private void initUI()
    {
        createPopupMenu();
        
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
        
        var maximizeMenuItem = new JMenuItem("Maximize");
        maximizeMenuItem.addActionListener((e) ->
        {
            if (getExtendedState() != JFrame.MAXIMIZED_BOTH)
            {
                setExtendedState(JFrame.MAXIMIZED_BOTH);
                maximizeMenuItem.setEnabled(false);
            }
        });
        
        popupMenu.add(maximizeMenuItem);
        var quitMenuItem = new JMenuItem("Quit");
        quitMenuItem.addActionListener((e) -> System.exit(0));
        
        popupMenu.add(quitMenuItem);
        
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent e)
            {
                if (getExtendedState() != JFrame.MAXIMIZED_BOTH)
                    maximizeMenuItem.setEnabled(true);
                
                if (e.getButton() == MouseEvent.BUTTON3)
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });
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
