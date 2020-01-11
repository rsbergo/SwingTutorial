package draganddrop;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.TransferHandler;

/**
 * Some Java Swing components, such as JLabel, do not have built-in drag support. This functionality must be coded.
 * 
 * This example shows how to drag-and-drop icons, using the icon property. There are three labels and one button, each
 * component displays an icon. The three labels enable drag gestures, the button accepts a drop gesture.
 */
public class IconDnD extends JFrame
{
    /**
     * Constructor.
     */
    public IconDnD()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        var icon1 = new ImageIcon("src/Resources/draganddrop/sad.png");
        var icon2 = new ImageIcon("src/Resources/draganddrop/plain.png");
        var icon3 = new ImageIcon("src/Resources/draganddrop/smile.png");
        
        var label1 = new JLabel(icon1, JLabel.CENTER);
        var label2 = new JLabel(icon2, JLabel.CENTER);
        var label3 = new JLabel(icon3, JLabel.CENTER);
        
        // The drag support is not enabled by default for the label. A custom mouse adapter is registered for the
        // labels.
        var listener = new DragMouseAdapter();
        label1.addMouseListener(listener);
        label2.addMouseListener(listener);
        label3.addMouseListener(listener);
        
        var button = new JButton(icon2);
        button.setFocusable(false);
        
        // Each of the components has a TransferHandler class for an icon property. The TransferHandler is needed for
        // both drag sources and drag targets.
        label1.setTransferHandler(new TransferHandler("icon"));
        label2.setTransferHandler(new TransferHandler("icon"));
        label3.setTransferHandler(new TransferHandler("icon"));
        button.setTransferHandler(new TransferHandler("icon"));
        
        createLayout(label1, label2, label3, button);
        
        setTitle("Icon Drag & Drop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    /**
     * Initiates the drag support. It gets the drag source (a label instance), gets its TransferHandler object and
     * initiates the drag support with the exportAsDrag() method call.
     */
    private class DragMouseAdapter extends MouseAdapter
    {
        public void mousePressed(MouseEvent e)
        {
            var c = (JComponent) e.getSource();
            var handler = c.getTransferHandler();
            handler.exportAsDrag(c, e, TransferHandler.COPY);
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
        
        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);
        
        gl.setHorizontalGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(gl.createSequentialGroup()
                        .addComponent(arg[0])
                        .addGap(30)
                        .addComponent(arg[1])
                        .addGap(30)
                        .addComponent(arg[2]))
                .addComponent(arg[3], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Integer.MAX_VALUE)
        );
        
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup()
                        .addComponent(arg[0])
                        .addComponent(arg[1])
                        .addComponent(arg[2]))
                .addComponent(arg[3])
        );
        
        pack();
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> 
        {
            var ex = new IconDnD();
            ex.setVisible(true);
        });
    }
    
}
