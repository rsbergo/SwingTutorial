package menusandtoolbars;

import java.awt.EventQueue;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JToolBar;

/**
 * It is often necessary to display more than one toolbar on the window.
 * 
 * This example displays two toolbars at the top of the window.
 */
public class ToolbarsEx extends JFrame
{
    /**
     * Constructor.
     */
    public ToolbarsEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        createToolBars();
        
        setTitle("Toolbars");
        setSize(360, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * Create the toolbars.
     */
    private void createToolBars()
    {
        var toolbar1 = new JToolBar();
        var toolbar2 = new JToolBar();
        
        var newIcon = new ImageIcon("src/Resources/new2.png");
        var openIcon = new ImageIcon("src/Resources/open2.png");
        var saveIcon = new ImageIcon("src/Resources/save2.png");
        var exitIcon = new ImageIcon("src/Resources/exit2.png");
        
        var newBtn = new JButton(newIcon);
        var openBtn = new JButton(openIcon);
        var saveBtn = new JButton(saveIcon);
        
        toolbar1.add(newBtn);
        toolbar1.add(openBtn);
        toolbar1.add(saveBtn);
        
        var exitBtn = new JButton(exitIcon);
        toolbar2.add(exitBtn);
        
        exitBtn.addActionListener((e) -> System.exit(0));
        
        createLayout(toolbar1, toolbar2);
    }
    
    /**
     * Create the layout.
     * 
     * GroupLayout manager is used to position the toolbars at the top of the container.
     */
    private void createLayout(JComponent... arg)
    {
        var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);
        
        gl.setHorizontalGroup(gl.createParallelGroup()
                .addComponent(arg[0], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(arg[1], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        
        gl.setVerticalGroup(gl.createSequentialGroup().addComponent(arg[0]).addComponent(arg[1]));
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> 
        {
            var ex = new ToolbarsEx();
            ex.setVisible(true);
        });
    }
    
}
