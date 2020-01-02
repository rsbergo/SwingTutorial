package firstprograms;

import java.awt.EventQueue;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * Mnemonics are shortcut keys that activate a component that supports mnemonics. For instance, they can be used with
 * labels, buttons, or menus.
 * 
 * The window contains a button with an action listener. A mnemonic is set for this button. The button can be activated
 * with the Alt+B keyboard shortcut.
 */
public class MnemonicEx extends JFrame
{
    /**
     * Constructor.
     */
    public MnemonicEx()
    {
        initUI();
    }
    
    /**
     * Initializes the UI.
     * 
     * There are three ways to activate the button: a left mouse click, the Alt+B shortcut, and the Space key (provided
     * the button has the focus). The Space key binding was automatically created by Swing. (Under Metal look and feel,
     * the focus is visually represented by a small rectangle around the button's label.)
     */
    private void initUI()
    {
        var btn = new JButton("Button");
        btn.addActionListener((event) -> System.out.println("Button pressed"));
        
        // setmnemonic() method sets a keyboard mnemonic for the button. The mnemonic key is specified with a virtual
        // keycode from the KeyEvent class. The mnemonic is combined with the look and feel's mouseless modifier
        // (usually Alt).
        btn.setMnemonic(KeyEvent.VK_B);
        
        createLayout(btn);
        
        setTitle("Mnemonics");
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
        gl.setHorizontalGroup(gl.createSequentialGroup().addComponent(arg[0]).addGap(200));
        gl.setVerticalGroup(gl.createSequentialGroup().addComponent(arg[0]).addGap(120));
        
        pack();
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new MnemonicEx();
            ex.setVisible(true);
        });
    }
    
}
