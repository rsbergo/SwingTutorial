package swinglayoutmanagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

/**
 * This example shows a typical usage of the BorderLayout manager.
 * 
 * This example shows a typical application skeleton. It shows a vertical and horizontal toolbars, a statusbar, and a
 * central component (a text area).
 */
public class BorderLayoutEx2 extends JFrame
{
    /**
     * Constructor.
     */
    public BorderLayoutEx2()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     * 
     * BorderLayout is the default layout manager of the JFrame container. It does not need to be set explicitly.
     */
    private void initUI()
    {
        setJMenuBar(createMenuBar());
        
        // The toolbar is placed to the north of the layout.
        add(createTopToolBar(), BorderLayout.NORTH);
        
        // The vertical toolbar is added to the west.
        add(createLeftToolBar(), BorderLayout.WEST);
        
        // The text area is placed into the center.
        add(new JTextArea(), BorderLayout.CENTER);
        
        // The statusbar goes to the south area.
        add(new JLabel(" Statusbar"), BorderLayout.SOUTH);
        
        setSize(400, 350);
        setTitle("BorderLayout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    /**
     * Create the menubar.
     */
    private JMenuBar createMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        return menuBar;
    }
    
    /**
     * Create the top toolbar.
     */
    private JToolBar createTopToolBar()
    {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.add(createTopToolBarButton(new ImageIcon("src/Resources/exit2.png"), (e) -> System.exit(0)));
        return toolBar;
    }
    
    /**
     * Create the left toolbar.
     */
    private JToolBar createLeftToolBar()
    {
        JToolBar toolBar = new JToolBar();
        toolBar.setOrientation(JToolBar.VERTICAL);
        toolBar.setFloatable(false);
        toolBar.setMargin(new Insets(10, 5, 5, 5));
        toolBar.add(createLeftToolBarButton(new ImageIcon("src/Resources/drive.png"), null));
        toolBar.add(createLeftToolBarButton(new ImageIcon("src/Resources/computer.png"), null));
        toolBar.add(createLeftToolBarButton(new ImageIcon("src/Resources/printer.png"), null));
        return toolBar;
    }
    
    /**
     * Create a toolbar button.
     */
    private JButton createToolBarButton(ImageIcon icon, ActionListener listener)
    {
        JButton btn = new JButton(icon);
        if (listener != null)
            btn.addActionListener(listener);
        return btn;
    }
    
    /**
     * Create a button for the top toolbar.
     */
    private JButton createTopToolBarButton(ImageIcon icon, ActionListener listener)
    {
        JButton btn = createToolBarButton(icon, listener);
        btn.setBorder(new EmptyBorder(0, 0, 0, 0));
        return btn;
    }
    
    /**
     * Create a button for the left toolbar.
     * 
     * To put some empty space around the button, an EmptyBorder is needed, adding some fixed space to the top and
     * bottom of the button. When fixed spaces are added, the UI is not portable. A 3px space may look OK on a 1280x720
     * screen, but it is inappropriate on a 1920x1200 screen.
     */
    private JButton createLeftToolBarButton(ImageIcon icon, ActionListener listener)
    {
        JButton btn = createToolBarButton(icon, listener);
        btn.setBorder(new EmptyBorder(3, 0, 3, 0));
        return btn;
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new BorderLayoutEx2();
            ex.setVisible(true);
        });
    }
}
