package basicswingcomponents;

import static javax.swing.LayoutStyle.ComponentPlacement.UNRELATED;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * JPasswordField is a JTextField subclass that does not show the characters that the user types.
 * 
 * This example has a text field, a password field, and a button. The button prints the data entered by the user.
 */
public class PasswordEx extends JFrame
{
    // Data Fields
    private JTextField loginField;
    private JPasswordField passField;
    
    // Methods
    /**
     * Constructor.
     */
    public PasswordEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        var lbl1 = new JLabel("Login");
        var lbl2 = new JLabel("Password");
        
        loginField = new JTextField(15);
        
        // Create an instance of JPasswordField.
        passField = new JPasswordField(15);
        
        var submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitAction());
        
        createLayout(lbl1, loginField, lbl2, passField, submitButton);
        
        setTitle("Login");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * SubmitAction inner class.
     */
    private class SubmitAction extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            doSubmitAction();
        }
        
        private void doSubmitAction()
        {
            var login = loginField.getText();
            
            // As a security precaution, a password field stores its value as an array of characters, rather than as a
            // string. The array of characters is returned by the getPassword() method. The older getText() has been
            // deprecated.
            var passwd = passField.getPassword();
            
            if (!login.isEmpty() && passwd.length != 0)
                System.out.format("User %s entered %s password%n", login, String.valueOf(passwd));
            
            // Once the password has been processed, it is recommended to set the array's elements to zero.
            Arrays.fill(passwd, '0');
        }
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
        gl.setAutoCreateContainerGaps(true);
        
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(50)
                .addGroup(gl.createParallelGroup()
                        .addComponent(arg[0])
                        .addComponent(arg[1])
                        .addComponent(arg[2])
                        .addComponent(arg[3])
                        .addComponent(arg[4]))
                .addGap(50)
        );
        
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(50)
                .addGroup(gl.createSequentialGroup()
                        .addComponent(arg[0])
                        .addComponent(arg[1], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(arg[2])
                        .addComponent(arg[3], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(UNRELATED)
                        .addComponent(arg[4]))
                .addGap(50)
        );
        
        pack();
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new PasswordEx();
            ex.setVisible(true);
        });
    }
    
}
