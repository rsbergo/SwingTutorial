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
import java.io.IOException;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This example inspects a simple drag gesture. Several classes are needed to create a drag gesture: DragSource,
 * DragGestureEvent, DragGestureListener, Transferable.
 * 
 * This example demonstrates a drag gesture. The drag gesture is created when a component is clicked and the mouse
 * pointer is moved while the button is pressed. The example shows how a DragSource can be created for a component.
 * 
 * DragGesture implements two interfaces: DragGestureListener listens for drag gestures, Transferable handles data for a
 * transfer operation. In this example, no data is transferred; it only demonstrates a drag gesture. The three necessary
 * methods of the Transferable interface are left unimplemented.
 */
public class DragGesture extends JFrame implements DragGestureListener, Transferable
{
    /**
     * Constructor.
     */
    public DragGesture()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        var redPanel = new JPanel();
        redPanel.setBackground(Color.red);
        redPanel.setPreferredSize(new Dimension(120, 120));
        
        // Create a DragSource object and register it for the pane. The DragSource is the entity responsible for the
        // initiation of the drag-and-drop operation. The createDefaultDragGestureRecognizer() associates a drag source
        // and the DragGestureListener with a particular component.
        var ds = new DragSource();
        ds.createDefaultDragGestureRecognizer(redPanel, DnDConstants.ACTION_COPY, this);
        
        createLayout(redPanel);
        
        setTitle("Drag Gesture");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    /**
     * The dragGestureRecognized() method responds to a drag gesture.
     * 
     * The startDrag() method of the DragGestureEvent finally starts the drag operation. Two parameters are specified:
     * the cursor type and the Transferable object.
     */
    public void dragGestureRecognized(DragGestureEvent event)
    {
        var cursor = Cursor.getDefaultCursor();
        
        if (event.getDragAction() == DnDConstants.ACTION_COPY)
            cursor = DragSource.DefaultCopyDrop;
        
        event.startDrag(cursor, this);
    }
    
    public Object getTransferData(DataFlavor flavor)
    {
        return null;
    }
    
    public DataFlavor[] getTransferDataFlavors()
    {
        return new DataFlavor[0];
    }
    
    public boolean isDataFlavorSupported(DataFlavor flavor)
    {
        return false;
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
                .addGap(50)
                .addComponent(arg[0])
                .addGap(50)
        );
        
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(50)
                .addComponent(arg[0])
                .addGap(50)
        );
        
        pack();
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> 
        {
            var ex = new DragGesture();
            ex.setVisible(true);
        });
    }
    
}
