package basicswingcomponents;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * A mnemonic is a key which when combined with the look and feel's mouseless modifier (usually Alt) will activate this
 * button if focus is contained within this button's ancestor window.
 * 
 * This example shows a button that can be activated with a mouse click or with a Alt+S keyboard shortcut.
 * 
 * The ButtonMnemonicEx class implements the ActionListener; it must override the actionPerformed() method where the
 * code that is executed after the button is activated is put.
 */
public class ButtonMnemonicEx extends JFrame implements ActionListener
{
    /**
     * Constructor
     */
    public ButtonMnemonicEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        // A new JButton is created and an action listener is added to it with the addActionListener() method. The
        // setMnemonic() sets a mnemonic key, the "S" character is underlined.
        var showBtn = new JButton("Show");
        showBtn.addActionListener(this);
        showBtn.setMnemonic(KeyEvent.VK_S);
        
        createLayout(showBtn);
        
        setTitle("JButton");
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
        
        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);
        
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
                .addGap(250)
        );
        
        gl.setVerticalGroup(gl.createParallelGroup()
                .addComponent(arg[0])
                .addGap(150)
        );
        
        pack();
    }
    
    /**
     * When the button is activated, either via a mouse click or via a shortcut, a message dialog is displayed with
     * JOptionPane.showMessageDialog().
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        JOptionPane.showMessageDialog(this, "Button Clicked", "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new ButtonMnemonicEx();
            ex.setVisible(true);
        });
    }
    
}
