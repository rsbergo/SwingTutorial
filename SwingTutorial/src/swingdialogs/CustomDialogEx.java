package swingdialogs;

import static javax.swing.GroupLayout.Alignment.CENTER;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * The AboutDialog class.
 * 
 * The custom dialog is based on the JDialog class.
 */
class AboutDialog extends JDialog
{
    /**
     * Constructor.
     */
    public AboutDialog(Frame parent)
    {
        super(parent);
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        var icon = new ImageIcon("src/Resources/swingdialogs/notes.png");
        var imgLabel = new JLabel(icon);
        
        var textLabel = new JLabel("Notes, 1.23");
        textLabel.setFont(new Font("Serig", Font.BOLD, 13));
        
        var okBtn = new JButton("OK");
        okBtn.addActionListener(event -> dispose());
        
        createLayout(textLabel, imgLabel, okBtn);
        
        // The setModalityType() method set the modality type of the dialog. The ModalityType.APPLICATION_MODAL blocks
        // input from all top-level windows of the same application. In this case, the input to the application's frame
        // is blocked during the lifetime of the dialog.
        setModalityType(ModalityType.APPLICATION_MODAL);
        
        setTitle("About Notes");
        
        // The setDefaultCloseOperation() sets what happens when the user clicks on the windoe's Close button. The
        // dialog will be hidden and disposed.
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        // The setLocationRelativeTo() method centers the dialog window over the area of the frame window.
        setLocationRelativeTo(getParent());
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
        
        gl.setHorizontalGroup(gl.createParallelGroup(CENTER)
                .addComponent(arg[0])
                .addComponent(arg[1])
                .addComponent(arg[2])
                .addGap(200)
        );
        
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(30)
                .addComponent(arg[0])
                .addGap(20)
                .addComponent(arg[1])
                .addGap(20)
                .addComponent(arg[2])
                .addGap(30)
        );
        
        pack();
    }
    
}

/**
 * This example creates a simple custom dialog. It is a sample about dialog found in many GUI applications, usually
 * located in the Help menu.
 * 
 * A small dialog box popup from the help menu. The dialog displays text, and icon, and a button. 
 */
public class CustomDialogEx extends JFrame implements ActionListener
{
    /**
     * Constructor
     */
    public CustomDialogEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        createMenuBar();
        
        setTitle("Simple Dialog");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * Create the menubar.
     */
    private void createMenuBar()
    {
        var menubar = new JMenuBar();
        
        var fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        
        var helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        
        var aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.setMnemonic(KeyEvent.VK_A);
        helpMenu.add(aboutMenuItem);
        
        aboutMenuItem.addActionListener(this);
        
        menubar.add(fileMenu);
        menubar.add(Box.createGlue());
        menubar.add(helpMenu);
        setJMenuBar(menubar);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        showAboutDialog();
    }
    
    /**
     * Show the about dialog.
     * 
     * The dialog window is shown on the screen with the setVisible() method.
     */
    private void showAboutDialog()
    {
        var aboutDialog = new AboutDialog(this);
        aboutDialog.setVisible(true);
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new CustomDialogEx();
            ex.setVisible(true);
        });
    }
    
}