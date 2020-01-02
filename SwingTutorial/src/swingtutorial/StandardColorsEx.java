package swingtutorial;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * JLabel component is a specific component with a default transparent background. In order to paint on a label,
 * isOpaque() is overridden, returning true.
 */
class MyLabel extends JLabel
{
    /**
     * Constructor.
     */
    public MyLabel()
    {
        super("", null, LEADING);
    }
    
    @Override
    public boolean isOpaque()
    {
        return true;
    }
    
}

/**
 * The Color class defines thirteen color values, including red, green, blue, and yellow.
 * 
 * Shows thirteen JLabel components; each of the labels has a different background color. JLabel is usually used to
 * display text; but it can display colors too.
 */
public class StandardColorsEx extends JFrame
{
    /**
     * Constructor
     */
    public StandardColorsEx()
    {
        initUI();
    }
    
    /**
     * Initializes the UI.
     */
    private void initUI()
    {
        // Array of built-in static color values.
        Color[] stdCols = { Color.black, Color.blue, Color.cyan, Color.darkGray, Color.gray, Color.green,
                            Color.lightGray, Color.magenta, Color.orange, Color.pink, Color.red, Color.white, 
                            Color.yellow };
        
        // A list of JLabel components is created. A new label is created with the createColouredLabel() method.
        var labels = new ArrayList<JLabel>();
        for (var col : stdCols)
        {
            var lbl = createColouredLabel(col);
            labels.add(lbl);
        }
        
        createLayout(labels.toArray(new JLabel[0]));
        
        setTitle("Standard colors");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * Create a label with the given color.
     * 
     * A minimum size is set for the new label created. The setBackground() sets the background color for a component.
     */
    private JLabel createColouredLabel(Color col)
    {
        var lbl = new MyLabel();
        lbl.setMinimumSize(new Dimension(90, 40));
        lbl.setBackground(col);
        
        return lbl;
    }
    
    /**
     * Create the layout groups.
     */
    private void createLayout(JLabel[] labels)
    {
        var pane = (JPanel) getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);
        
        pane.setToolTipText("Content pane");
        
        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);
        
        gl.setHorizontalGroup(gl.createParallelGroup()
                .addGroup(gl.createSequentialGroup()
                        .addComponent(labels[0])
                        .addComponent(labels[1])
                        .addComponent(labels[2])
                        .addComponent(labels[3]))
                .addGroup(gl.createSequentialGroup()
                        .addComponent(labels[4])
                        .addComponent(labels[5])
                        .addComponent(labels[6])
                        .addComponent(labels[7]))
                .addGroup(gl.createSequentialGroup()
                        .addComponent(labels[8])
                        .addComponent(labels[9])
                        .addComponent(labels[10])
                        .addComponent(labels[11]))
                .addComponent(labels[12]));
        
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup()
                        .addComponent(labels[0])
                        .addComponent(labels[1])
                        .addComponent(labels[2])
                        .addComponent(labels[3]))
                .addGroup(gl.createParallelGroup()
                        .addComponent(labels[4])
                        .addComponent(labels[5])
                        .addComponent(labels[6])
                        .addComponent(labels[7]))
                .addGroup(gl.createParallelGroup()
                        .addComponent(labels[8])
                        .addComponent(labels[9])
                        .addComponent(labels[10])
                        .addComponent(labels[11]))
                .addComponent(labels[12]));
        
        pack();
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> 
        {
            var ex = new StandardColorsEx();
            ex.setVisible(true);
        });
    }
    
}
