package swingdialogs;

import static javax.swing.GroupLayout.DEFAULT_SIZE;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 * JColorChooser is a standard dialog for selecting a color.
 * 
 * In this example, the background color of the panel is changed by selecting a color from the JColorChooser.
 */
public class ColorChooserEx extends JFrame
{
    // Data Fields
    private JPanel colorPanel;
    
    // Methods
    /**
     * Constructor.
     */
    public ColorChooserEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        colorPanel = new JPanel();
        colorPanel.setBackground(Color.WHITE);
        
        var toolbar = createToolbar();
        
        createLayout(toolbar, colorPanel);
        
        setTitle("JColorChooser");
        setSize(400,  300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * Create the toolbar.
     */
    private JToolBar createToolbar()
    {
        var openIcon = new ImageIcon("src/Resources/swingdialogs/colourdlg.png");
        
        var toolbar = new JToolBar();
        var openBtn = new JButton(openIcon);
        
        openBtn.addActionListener(e -> 
        {
            // The showDialog() method returns the selected color value. The colorPanel's background is changed to the
            // newly selected color.
            var color = JColorChooser.showDialog(colorPanel, "Choose color", Color.white);
            colorPanel.setBackground(color);
        });
        
        toolbar.add(openBtn);
        return toolbar;
    }
    
    /**
     * Create the layout.
     */
    private void createLayout(JComponent... arg)
    {
        var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);
        
        gl.setHorizontalGroup(gl.createParallelGroup()
                .addComponent(arg[0], DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(gl.createSequentialGroup()
                        .addGap(30)
                        .addComponent(arg[1])
                        .addGap(30))
        );
        
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
                .addGap(30)
                .addComponent(arg[1])
                .addGap(30)
        );
        
        pack();
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> 
        {
            var ex = new ColorChooserEx();
            ex.setVisible(true);
        });
    }
    
}
