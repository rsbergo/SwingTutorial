package basicswingcomponents2;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

/**
 * JList is a component that displays a list of objects. It allows the user to select one or more items.
 * 
 * This example displays a JList and a JLabel components. The list component contains a list of all available font
 * family names on the system. If an item from the list is selected, the label will be displayed in the chosen format.
 */
public class ListEx extends JFrame
{
    // Data Fields
    private JLabel label;
    private JScrollPane spane;
    
    // Methods
    /**
     * Constructor.
     */
    public ListEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        // Get all the possible font family names on the system and create a JList component.
        var ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        var fonts = ge.getAvailableFontFamilyNames();
        var list = new JList(fonts);
        
        // Events in list selection are grouped. Events for both selecting and deselecting of items are received. To
        // filter only the selecting events, use the getValueIsAdjusting() method. 
        list.addListSelectionListener(e -> 
        {
            if (!e.getValueIsAdjusting())
            {
                // Get the selected item and set a new font for the label.
                var name = (String) list.getSelectedValue();
                var font = new Font(name, Font.PLAIN, 12);
                
                label.setFont(font);
            }
        });
        
        // JList can have more items than it is physically possible to show on the window. It is not scrollable by
        // default. Put the list into the JScrollPane to make it scrollable.
        spane = new JScrollPane();
        spane.getViewport().add(list);
        
        label = new JLabel("Aguirre, der Zorn Gottes");
        label.setFont(new Font("Serif", Font.PLAIN, 12));
        
        createLayout(spane, label);
        
        setTitle("JList");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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
        
        gl.setHorizontalGroup(gl.createParallelGroup()
                .addComponent(arg[0])
                .addComponent(arg[1])
        );
        
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
                .addComponent(arg[1])
        );
        
        pack();
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> 
        {
            var ex = new ListEx();
            ex.setVisible(true);
        });
    }
    
}
