package draganddrop;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This example creates a complex drag-and-drop example. A drag source, a drop target, and a transferable object are
 * created.
 * 
 * This example shows a button and two panels. The button displays a color chooser dialog and sets a color for the first
 * panel. The color can be dragged into the second panel.
 */
public class ComplexDnD extends JFrame implements DragGestureListener
{
    // Data Fields
    private JPanel leftPanel;
    
    // Methods
    /**
     * Constructor.
     */
    public ComplexDnD()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        var colorBtn = new JButton("Choose Color");
        colorBtn.setFocusable(false);
        
        leftPanel = new JPanel();
        leftPanel.setBackground(Color.red);
        leftPanel.setPreferredSize(new Dimension(100, 100));
        
        colorBtn.addActionListener(event ->
        {
            var color = JColorChooser.showDialog(this, "Choose Color", Color.white);
            leftPanel.setBackground(color);
        });
        
        var rightPanel = new JPanel();
        rightPanel.setBackground(Color.white);
        rightPanel.setPreferredSize(new Dimension(100, 100));
        
        // Register a drop target listener with the right panel.
        var mtl = new MyDropTargetListener(rightPanel);
        
        var ds = new DragSource();
        ds.createDefaultDragGestureRecognizer(leftPanel, DnDConstants.ACTION_COPY, this);
        
        createLayout(colorBtn, leftPanel, rightPanel);
        
        setTitle("Complex drag-and-drop example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    public void dragGestureRecognized(DragGestureEvent event)
    {
        var cursor = Cursor.getDefaultCursor();
        var panel = (JPanel) event.getComponent();
        
        var color = panel.getBackground();
        
        if (event.getDragAction() == DnDConstants.ACTION_COPY)
            cursor = DragSource.DefaultCopyDrop;
        
        // The startDrag() method has two parameters: a cursor and a Transferable object.
        event.startDrag(cursor, new TransferableColor(color));
    }
    
    private class MyDropTargetListener extends DropTargetAdapter
    {
        // Data Fields
        private final DropTarget dropTarget;
        private final JPanel panel;
        
        // Methods
        /**
         * Constructor, create a drop target object.
         */
        public MyDropTargetListener(JPanel panel)
        {
            this.panel = panel;
            dropTarget = new DropTarget(panel, DnDConstants.ACTION_COPY, this, true, null);
        }
        
        public void drop(DropTargetDropEvent event)
        {
            try
            {
                // Get the data being transferred (a color object). Set the color of the right panel.
                var tr = event.getTransferable();
                var col = (Color) tr.getTransferData(TransferableColor.colorFlavor);
                
                if (event.isDataFlavorSupported(TransferableColor.colorFlavor))
                {
                    event.acceptDrop(DnDConstants.ACTION_COPY);
                    this.panel.setBackground(col);
                    event.dropComplete(true);
                    return;
                }
                
                // If the conditions for a drag-and-drop operation are not fulfilled, reject it.
                event.rejectDrop();
            }
            catch (Exception e)
            {
                e.printStackTrace();
                event.rejectDrop();
            }
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
        
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
                .addGap(30)
                .addComponent(arg[1])
                .addGap(30)
                .addComponent(arg[2])
        );
        
        gl.setVerticalGroup(gl.createParallelGroup()
                .addComponent(arg[0])
                .addComponent(arg[1])
                .addComponent(arg[2])
        );
        
        pack();
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new ComplexDnD();
            ex.setVisible(true);
        });
    }
    
}

class TransferableColor implements Transferable
{
    // Data fields
    // Create a new DataFlavor object
    protected static final DataFlavor colorFlavor = new DataFlavor(Color.class, "A Color Object");
    // Specify what data flavors are supported (a custom defined color flavor and a predefined DataFlavor.stringFlavor).
    protected static final DataFlavor[] supportedFlavors = { colorFlavor, DataFlavor.stringFlavor };
    
    private final Color color;
    
    // Methods
    public TransferableColor(Color color)
    {
        this.color = color;
    }
    
    public DataFlavor[] getTransferDataFlavors()
    {
        return supportedFlavors;
    }
    
    public boolean isDataFlavorSupported(DataFlavor flavor)
    {
        return flavor.equals(colorFlavor) || flavor.equals(DataFlavor.stringFlavor);
    }
    
    /**
     * Return an object for a specific data flavor.
     */
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException
    {
        if (flavor.equals(colorFlavor))
            return color;
        else if (flavor.equals(DataFlavor.stringFlavor))
            return color.toString();
        else 
            throw new UnsupportedFlavorException(flavor);
    }
}