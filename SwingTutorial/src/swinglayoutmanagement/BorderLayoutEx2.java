package swinglayoutmanagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Insets;
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
        var menuBar = new JMenuBar();
        var fileMenu = new JMenu("File");
        
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        
        var toolBar = new JToolBar();
        toolBar.setFloatable(false);
        
        var exitIcon = new ImageIcon("src/Resources/exit2.png");
        var exitBtn = new JButton(exitIcon);
        exitBtn.setBorder(new EmptyBorder(0, 0, 0, 0));
        toolBar.add(exitBtn);
        
        // The toolbar is placed to the north of the layout.
        add(toolBar, BorderLayout.NORTH);
        
        var vertical = new JToolBar(JToolBar.VERTICAL);
        vertical.setFloatable(false);
        vertical.setMargin(new Insets(10, 5, 5, 5));
        
        var driveIcon = new ImageIcon("src/Resources/drive.png");
        var compIcon = new ImageIcon("src/Resources/computer.png");
        var printIcon = new ImageIcon("src/Resources/printer.png");
        
        // To put some empty space around the button, an EmptyBorder is needed, adding some fixed space to the top and
        // bottom of the button. When fixed spaces are added, the UI is not portable. A 3px space may look OK on a
        // 1280x720 screen, but it is inappropriate on a 1920x1200 screen.
        var driveBtn = new JButton(driveIcon);
        driveBtn.setBorder(new EmptyBorder(3, 0, 3, 0));
        
        var compBtn = new JButton(compIcon);
        compBtn.setBorder(new EmptyBorder(3, 0, 3, 0));
        
        var printBtn = new JButton(printIcon);
        printBtn.setBorder(new EmptyBorder(3, 0, 3, 0));
        
        vertical.add(driveBtn);
        vertical.add(compBtn);
        vertical.add(printBtn);
        
        // The vertical toolbar is added to the west.
        add(vertical, BorderLayout.WEST);
        
        // The text area is placed into the center.
        add(new JTextArea(), BorderLayout.CENTER);
        
        // The statusbar goes to the south area.
        var statusbar = new JLabel(" Statusbar");
        add(statusbar, BorderLayout.SOUTH);
        
        setSize(400, 350);
        setTitle("BorderLayout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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
