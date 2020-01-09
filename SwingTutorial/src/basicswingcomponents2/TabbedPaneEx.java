package basicswingcomponents2;

import java.awt.EventQueue;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * JTabbedPane is a component that lets a user switch between a group of components by clicking on a tab.
 * 
 * This example shows a tabbed pane with three tabs. Each of the tabs shows a panel with a label.
 */
public class TabbedPaneEx extends JFrame
{
    /**
     * Constructor.
     */
    public TabbedPaneEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        // Create a JTabbedPane.
        var tabbedPane = new JTabbedPane();
        
        // Create a new tab with the addTab() method. The first parameter is the title displayed by the tab. The second
        // parameter is a component to be displayed when the tab is clicked.
        tabbedPane.addTab("First", createPanel("First Panel"));
        tabbedPane.addTab("Second", createPanel("Second Panel"));
        tabbedPane.addTab("Third", createPanel("Third Panel"));
        
        createLayout(tabbedPane);
        
        setTitle("JTabbedPane");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * Create a panel that displays the given text.
     */
    private JPanel createPanel(String text)
    {
        var panel = new JPanel();
        var lbl = new JLabel(text);
        panel.add(lbl);
        
        return panel;
    }
    
    /**
     * Create the layout.
     */
    private void createLayout(JComponent... arg)
    {
        var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);
        
        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);
        
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );
        
        gl.setVerticalGroup(gl.createParallelGroup()
                .addComponent(arg[0])
        );
        
        pack();
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> 
        {
            var ex = new TabbedPaneEx();
            ex.setVisible(true);
        });
    }
    
}
