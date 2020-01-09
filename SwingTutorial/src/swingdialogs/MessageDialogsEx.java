package swingdialogs;

import static javax.swing.GroupLayout.DEFAULT_SIZE;
import java.awt.EventQueue;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * A dialog is defined as a conversation between two or more persons. In a computer application, a dialog is a window
 * which is used to "talk" to the application. A dialog is used to input data, modify data, change the application
 * settings, etc. Dialogs are important means of communication between a user and a computer program.
 * 
 * In Java Swing, two kinds of dialog can be created: standard dialogs and custom dialogs. Custom dialogs are created by
 * programmers based on the JDialog. Standard dialogs are predefined dialogs available in the Swing toolkit
 * (JColorChooser, JFileChooser, etc.) for common programming tasks like showing text, receiving input, loading and
 * saving files, etc.
 * 
 * There are two basic types of dialogs: modal and modeless. Modal dialogs block input to other top-level windows (e.g.
 * open file dialog). Modeless dialogs allow input to other windows (e.g. find text dialog).
 * 
 * Message dialogs are simple dialogs that provide information to the user. Message dialogs are created with the
 * JOptionPane.showMessageDialog() method.
 * 
 * This example shows an error, a warning, a question, and an information message dialog.
 */
public class MessageDialogsEx extends JFrame
{
    // Data Fields
    private JPanel pnl;
    
    // Methods
    /**
     * Constructor.
     */
    public MessageDialogsEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        pnl = (JPanel) getContentPane();
        
        // Each button shows a different message dialog.
        var warBtn = new JButton("Warning");
        var errBtn = new JButton("Error");
        var queBtn = new JButton("Question");
        var infBtn = new JButton("Information");
        
        // To create a message dialog, call the static showMessageDialog() method of the JOptionPane class, providing
        // the dialog's parent, message text, title and message type (ERROR_MESSAGE, WARNING_MESSAGE, QUESTION_MESSAGE,
        // INFORMATION_MESSAGE). The displayed icon depends on the message type.
        warBtn.addActionListener((event) -> JOptionPane.showMessageDialog(pnl, "A deprecated call!", "Warning", JOptionPane.WARNING_MESSAGE));
        errBtn.addActionListener((event) -> JOptionPane.showMessageDialog(pnl, "Could not open file!", "Error", JOptionPane.ERROR_MESSAGE));
        queBtn.addActionListener((event) -> JOptionPane.showMessageDialog(pnl, "Are you sure to quit?", "Question", JOptionPane.QUESTION_MESSAGE));
        infBtn.addActionListener((event) -> JOptionPane.showMessageDialog(pnl, "Download completed", "Information", JOptionPane.INFORMATION_MESSAGE));
        
        createLayout(warBtn, errBtn, queBtn, infBtn);
        
        setTitle("Message dialogs");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * Create the layout.
     */
    private void createLayout(JComponent... arg)
    {
        var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);
        
        gl.setAutoCreateGaps(true);
        
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addContainerGap(DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(gl.createParallelGroup()
                        .addComponent(arg[0])
                        .addComponent(arg[2]))
                .addGroup(gl.createParallelGroup()
                        .addComponent(arg[1])
                        .addComponent(arg[3]))
                .addContainerGap(DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addContainerGap(DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(gl.createParallelGroup()
                        .addComponent(arg[0])
                        .addComponent(arg[1]))
                .addGroup(gl.createParallelGroup()
                        .addComponent(arg[2])
                        .addComponent(arg[3]))
                .addContainerGap(DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        gl.linkSize(arg[0], arg[1], arg[2], arg[3]);
        
        pack();
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> 
        {
            var ex = new MessageDialogsEx();
            ex.setVisible(true);
        });
    }
    
}
