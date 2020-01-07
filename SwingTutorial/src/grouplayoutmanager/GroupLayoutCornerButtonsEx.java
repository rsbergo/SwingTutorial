package grouplayoutmanager;

import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

/**
 * This example places two buttons in the bottom-right corner of the window. The buttons are made the same size.
 */
public class GroupLayoutCornerButtonsEx extends JFrame
{
    /**
     * Constructor.
     */
    public GroupLayoutCornerButtonsEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        setPreferredSize(new Dimension(300, 200));
        
        var cpane = getContentPane();
        var gl = new GroupLayout(cpane);
        cpane.setLayout(gl);
        
        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);
        
        var okButton = new JButton("OK");
        var closeButton = new JButton("Close");
        
        // In the horizontal layout, a stretchable gap and two single components are added. The stretchable gap pushes
        // the two buttons to the right. The gap is created with the addPreferredGap() method call. Its parameters are
        // the type of the gap, the preferred and the minimum sizes of the gap. The difference between the maximum and
        // the preferred values is the ability of the gap to stretch. When both values are the same, the gap has a fixed
        // size.
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(okButton)
                .addComponent(closeButton)
        );
        
        // In the vertical layout, a stretchable gap and a parallel group of two components are added. The gap pushes
        // the group of buttons to the bottom.
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(gl.createParallelGroup()
                        .addComponent(okButton)
                        .addComponent(closeButton))
        );
        
        // The linkSize() method makes both buttons the same size. Only the width needs to be set, since their height is
        // the same by default.
        gl.linkSize(SwingConstants.HORIZONTAL, okButton, closeButton);
        
        pack();
        
        setTitle("Buttons");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new GroupLayoutCornerButtonsEx();
            ex.setVisible(true);
        });
    }
    
}
