package draganddrop;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.datatransfer.DataFlavor;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;

/**
 * Some components, such as the JList, do not have a default drop support. There is a good reason for this: it is not
 * known if the data will be inserted into one row, or two or more rows. The drop support for the list component must be
 * implemented manually.
 * 
 * This example inserts comma or space separated text into the rows of a JList component. Otherwise, the text goes in
 * one row.
 * 
 * This example contains a text field and a list component. The text in the text field can be dragged and dropped into
 * the list. If the text is comma separated with a comma or a space character, the words will be split into rows. Of
 * not, the text is inserted into one row.
 */
public class ListDnD extends JFrame
{
    // Data Fields
    private JTextField field;
    private DefaultListModel model;
    
    // Methods
    /**
     * Constructor.
     */
    public ListDnD()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        var scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(180, 150));
        
        model = new DefaultListModel();
        var myList = new JList(model);
        
        // Specify the drop mode. The DropMode.INSERT specifies that new items are going to be inserted into the list
        // component, i.e. new items are dropped onto the existing ones.
        myList.setDropMode(DropMode.INSERT);
        myList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Set a custom transfer handler class.
        myList.setTransferHandler(new ListHandler());
        
        field = new JTextField(15);
        
        // Enable the drag support for the text field component.
        field.setDragEnabled(true);
        
        scrollPane.getViewport().add(myList);
        
        createLayout(field, scrollPane);
        
        setTitle("ListDrop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private class ListHandler extends TransferHandler
    {
        /**
         * Tests the suitability of a drop operation. Filter out the clipboard past operations and allow only String
         * drop operations. If the method returns false, the drop operation is cancelled.
         */
        public boolean canImport(TransferSupport support)
        {
            if (!support.isDrop())
                return false;
            
            return support.isDataFlavorSupported(DataFlavor.stringFlavor);
        }
        
        /**
         * Transfers the data from the clipboard or from the drag-and-drop operation to the drop location.
         */
        public boolean importData(TransferSupport support)
        {
            if (!canImport(support))
                return false;
            
            // The Transferable is the class where the data is bundled.
            var transferable = support.getTransferable();
            String line;
            
            // Retrieve the data.
            try
            {
                line = (String) transferable.getTransferData(DataFlavor.stringFlavor);
            }
            catch (Exception e)
            {
                return false;
            }
            
            // Get a drop location for the list. Retrieve the index where the data will be inserted.
            var dl = (JList.DropLocation) support.getDropLocation();
            int index = dl.getIndex();
            
            // Slipt the text into parts and insert it into one or more rows.
            String[] data = line.split("[,\\s]");
            
            for (String item : data)
            {
                
                if (!item.isEmpty())
                    model.add(index++, item.trim());
            }
            
            return true;
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
                .addComponent(arg[1])
        );
        
        gl.setVerticalGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
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
            var ex = new ListDnD();
            ex.setVisible(true);
        });
    }
    
}
