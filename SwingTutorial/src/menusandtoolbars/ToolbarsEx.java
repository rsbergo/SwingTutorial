package menusandtoolbars;

import java.awt.Container;
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
        JToolBar toolbar1 = createToolBar1();
        JToolBar toolbar2 = createToolBar2();
        
        
        createLayout(toolbar1, toolbar2);
    }
    
    /**
     * Create the first toolbar.
     */
    private JToolBar createToolBar1()
    {
        JToolBar toolbar = new JToolBar();
        
        toolbar.add(createNewToolBarButton());
        toolbar.add(createOpenToolBarButton());
        toolbar.add(createSaveToolBarButton());
        
        return toolbar;
    }
    
    /**
     * Create the second toolbar.
     * @return
     */
    private JToolBar createToolBar2()
    {
        JToolBar toolbar = new JToolBar();
        toolbar.add(createExitToolBarButton());
        return toolbar;
    }
    
    /**
     * Create a toolbar button.
     */
    private JButton createToolBarButton(ImageIcon icon)
    {
        return new JButton(icon);
    }
    
    /**
     * Create the new button for a toolbar.
     */
    private JButton createNewToolBarButton()
    {
        return createToolBarButton(new ImageIcon("src/Resources/new2.png"));
    }
    
    /**
     * Create the open button for a toolbar.
     */
    private JButton createOpenToolBarButton()
    {
        return createToolBarButton(new ImageIcon("src/Resources/open2.png"));
    }
    
    /**
     * Create the save button for a toolbar.
     */
    private JButton createSaveToolBarButton()
    {
        return createToolBarButton(new ImageIcon("src/Resources/save2.png"));
    }
    
    /**
     * Create the exit button for a toolbar.
     */
    private JButton createExitToolBarButton()
    {
        JButton btn = createToolBarButton(new ImageIcon("src/Resources/exit2.png"));
        btn.addActionListener((e) -> System.exit(0));
        return btn;
    }
    
    /**
     * Create the layout.
     * 
     * GroupLayout manager is used to position the toolbars at the top of the container.
     */
    private void createLayout(JComponent... arg)
    {
        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);
        
        // Create the horizontal group for GroupLayout.
        GroupLayout.ParallelGroup hGroup = gl.createParallelGroup();
        hGroup.addComponent(arg[0], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        hGroup.addComponent(arg[1], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        
        // Create the vertical group for GroupLayout.
        GroupLayout.SequentialGroup vGroup = gl.createSequentialGroup();
        vGroup.addComponent(arg[0]);
        vGroup.addComponent(arg[1]);
        
        // Set both groups to GroupLayout.
        gl.setHorizontalGroup(hGroup);
        gl.setVerticalGroup(vGroup);
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
