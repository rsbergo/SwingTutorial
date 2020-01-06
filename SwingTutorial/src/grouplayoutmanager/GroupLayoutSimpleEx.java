package grouplayoutmanager;

import java.awt.EventQueue;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import static javax.swing.GroupLayout.Alignment.LEADING;
import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;

/**
 * This is the only built-in manager that can create multi-platform layouts. All other managers are either very
 * simplistic or use fixed sized gaps that are not suitable for user interfaces on different platforms and screen
 * resolutions. The third-party MigLayout can also be used to create multi-platform layouts in Java.
 * 
 * GroupLayout separates components from the actual layout; all components can be set up in one place and the layout in
 * another one. It defines the layout for each dimension independently. In one dimension, components are placed
 * alongside the horizontal axis; in the other dimension, components are placed along the vertical axis. In both kinds
 * of layouts, components can be arranged sequentially or in parallel. In a horizontal layout, a row of components is
 * called a sequential group and a column of components is called a parallel group. In a vertical layout, a column of
 * components is called a sequential group and a row of components is called a parallel group.
 * 
 * GroupLayout uses three types of gaps between components or components and borders: RELATED (used for related
 * components), UNRELATED (used for unrelated components), and INDENTED (used for indents between components). These
 * gaps are resolution independent; i.e. they have different size in pixels on different resolution screens. Other
 * built-in managers use fixes size gaps on all resolutions.
 * 
 * A component is added to the layout manager with the addComponent() method. The parameters are the minimum, preferred,
 * and maximum size values. Either specific absolute values can be passed, or the GroupLayout.DEFAULT_SIZE or the
 * GroupLayout.PREFERRED_SIZE can be provided. The GroupLayout.DEFAULT_SIZE indicates that the corresponding size from
 * the component should be used. The GroupLayout.PREFERRED_SIZE is determined by calling the component's
 * getPreferredSize() method.
 * 
 * In this example, a label and a text field are shown. The text field is non-stretchable.
 */
public class GroupLayoutSimpleEx extends JFrame
{
    /**
     * Constructor.
     */
    public GroupLayoutSimpleEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);
        
        var lbl = new JLabel("Name:");
        var field = new JTextField(15);
        
        // The text field's maximum size was changed to GroupLayout.PREFERRED_SIZE, making it non-expandable in the
        // horizontal direction beyond its preferred size. The difference between the preferred size and the maximum
        // size is the component's tendency to grow. This applies to managers that honor these values.
        GroupLayout.SequentialGroup sg = gl.createSequentialGroup();
        
        sg.addComponent(lbl).addPreferredGap(RELATED).addComponent(field, GroupLayout.DEFAULT_SIZE,
                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        
        gl.setHorizontalGroup(sg);
        
        // In the vertical layout, createParallelGroupt() receives false for its second parameter. This way, the text
        // field is prevented from growing in the vertical direction. The same can be achieved by setting the max
        // parameter of the addComponent() to GroupLayout.PREFERRED_SIZE, which is called in the vertical layout.
        GroupLayout.ParallelGroup pg = gl.createParallelGroup(LEADING, false);
        
        pg.addComponent(lbl).addComponent(field);
        gl.setVerticalGroup(pg);
        
        gl.setAutoCreateContainerGaps(true);
        
        pack();
        
        setTitle("Simple");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new GroupLayoutSimpleEx();
            ex.setVisible(true);
        });
    }
    
}
