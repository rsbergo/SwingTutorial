package grouplayoutmanager;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.TRAILING;
import java.awt.EventQueue;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * The requirements are: the labels must be aligned in the horizontal direction, and they must be vertically aligned to
 * their baseline with their corresponding text fields.
 */
public class GroupLayoutPasswordEx extends JFrame
{
    /**
     * Constructor.
     */
    public GroupLayoutPasswordEx()
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
        
        var serviceLbl = new JLabel("Service:");
        var userNameLbl = new JLabel("User name:");
        var passwordLbl = new JLabel("Password:");
        
        var field1 = new JTextField(10);
        var field2 = new JTextField(10);
        var field3 = new JTextField(10);
        
        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);
        
        // Horizontally, the layout consists of two parallel groups packed in a sequential group. Labels and fields are
        // put separately into their parallel groups. The parallel group of labels has the
        // GroupLayout.Alignment.Trailing alignment, which makes the labels right aligned.
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup(TRAILING)
                        .addComponent(serviceLbl)
                        .addComponent(userNameLbl)
                        .addComponent(passwordLbl))
                .addGroup(gl.createParallelGroup()
                        .addComponent(field1)
                        .addComponent(field2)
                        .addComponent(field3))
        );
        
        // In the vertical layout, the labels are aligned with their text fields to their baseline. To do this, the
        // labels and their corresponding fields are grouped into parallel groups with the
        // GroupLayout.Alignment.BASELINE alignment.
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(serviceLbl)
                        .addComponent(field1))
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(userNameLbl)
                        .addComponent(field2))
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(passwordLbl)
                        .addComponent(field3))
        );
        
        pack();
        
        setTitle("Password application");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> 
        {
            var ex = new GroupLayoutPasswordEx();
            ex.setVisible(true);
        });
    }
    
}
