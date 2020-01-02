package firstprograms;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * MouseMotionAdapter is used for receiving mouse motion events.
 * 
 * The coordinates of a mouse pointer are displayed in a label component.
 */
public class MouseMoveEventEx extends JFrame
{
    // Data fields
    private JLabel coords;
    
    // Methods
    /**
     * Constructor.
     */
    public MouseMoveEventEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        coords = new JLabel("");
        createLayout(coords);
        
        // Overrides the mouseMoved() method of the adapter. From the MouseEvent object, it is possible to get the x and
        // y coordinates of the mouse pointer, build a string and set it to the label.
        addMouseMotionListener(new MouseMotionAdapter()
        {
            @Override
            public void mouseMoved(MouseEvent e)
            {
                super.mouseMoved(e);
                int x = e.getX();
                int y = e.getY();
                
                var text = String.format("x: %d, y: %d", x, y);
                
                coords.setText(text);
            }
        });
        
        setTitle("Mouse move events");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * Create the layout groups.
     */
    private void createLayout(JComponent... arg)
    {
        var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);
        
        gl.setAutoCreateContainerGaps(true);
        gl.setHorizontalGroup(gl.createParallelGroup().addComponent(arg[0]).addGap(250));
        gl.setVerticalGroup(gl.createSequentialGroup().addComponent(arg[0]).addGap(130));
        
        pack();
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new MouseMoveEventEx();
            ex.setVisible(true);
        });
    }
    
}
