package swingmodels;

import static javax.swing.GroupLayout.Alignment.CENTER;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 * Several components have two models; JList is one of them. It has the ListModel, which handles data, and the
 * ListSelectionModel, which works with the selection state of the list.
 * 
 * This example uses both models. It shows a list component and four buttons. The buttons control the data in the list
 * component. This example is a bit larger because there are some additional checks. For instance, it does not allow to
 * input empty spaces into the list component.
 * 
 * This example uses both list models. The add(), remove(), and clear() methods of the list data model are called to
 * work with the data. A list selection model is used in order to find out the selected item.
 */
public class ListModelsEx extends JFrame
{
    // Data Fields
    private DefaultListModel<String> model;
    private JList<String> myList;
    private JButton remAllBtn;
    private JButton addBtn;
    private JButton renBtn;
    private JButton delBtn;
    
    // Methods
    /**
     * Constructor.
     */
    public ListModelsEx()
    {
        initUI();
    }
    
    /**
     * Create the list.
     */
    private void createList()
    {
        // Create a default list model and add elements to it.
        model = new DefaultListModel<>();
        model.addElement("Amelie");
        model.addElement("Aguirre, der Zorn Gottes");
        model.addElement("Fargo");
        model.addElement("Exorcist");
        model.addElement("Schindler's myList");
        
        // Create a list component. The parameter of the constructor is the model. The list is made into the single
        // selection mode.
        myList = new JList<>(model);
        myList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        myList.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (e.getClickCount() == 2)
                {
                    int index = myList.locationToIndex(e.getPoint());
                    var item = model.getElementAt(index);
                    var text = JOptionPane.showInputDialog("Rename item", item);
                    
                    String newItem;
                    
                    if (text != null)
                        newItem = text.trim();
                    else
                        return;
                    
                    if (!newItem.isEmpty())
                    {
                        model.remove(index);
                        model.add(index, newItem);
                        
                        var selModel = myList.getSelectionModel();
                        selModel.setLeadSelectionIndex(index);
                    }
                }
            }
        });
    }
    
    /** 
     * Create the buttons.
     */
    private void createButtons()
    {
        remAllBtn = new JButton("Remove All");
        addBtn = new JButton("Add");
        renBtn = new JButton("Rename");
        delBtn = new JButton("Delete");
        
        addBtn.addActionListener(e -> 
        {
            var text = JOptionPane.showInputDialog("Add a new item");
            String item;
            
            // Add only items that are not equal to null and are not empty, e.g. items that contain at least one
            // character other than white space. It makes no sense to add white spaces or null values into the list.
            if (text != null)
                item = text.trim();
            else
                return;
            
            if (!item.isEmpty())
                model.addElement(item);
        });
        
        delBtn.addActionListener(event -> 
        {
            // This code runs when the Delete button is pressed. In order to delete an item from the list, it must be
            // selected. To figure out the currently selected item, call the geSelectionModel() method. The selected
            // index can be retrieved with the getMinSelectionIndex() and the item can be removed with the remove()
            // method.
            var selModel = myList.getSelectionModel();
            
            int index = selModel.getMinSelectionIndex();
            
            if (index >= 0)
                model.remove(index);
        });
        
        renBtn.addActionListener(e -> 
        {
            var selModel = myList.getSelectionModel();
            int index = selModel.getMinSelectionIndex();
            
            if (index == -1)
                return;
            
            var item = model.getElementAt(index);
            var text = JOptionPane.showInputDialog("Rename item", item);
            String newItem;
            
            if (text != null)
                newItem = text.trim();
            else 
                return;
            
            if (!newItem.isEmpty())
            {
                model.remove(index);
                model.add(index, newItem);
            }
        });
        
        remAllBtn.addActionListener(e -> model.clear());
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        createList();
        createButtons();
        
        var scrollPane = new JScrollPane(myList);
        createLayout(scrollPane, addBtn, renBtn, delBtn, remAllBtn);
        
        setTitle("JList models");
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
        gl.setAutoCreateGaps(true);
        
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
                .addGroup(gl.createParallelGroup()
                        .addComponent(arg[1])
                        .addComponent(arg[2])
                        .addComponent(arg[3])
                        .addComponent(arg[4]))
        );
        
        gl.setVerticalGroup(gl.createParallelGroup(CENTER)
                .addComponent(arg[0])
                .addGroup(gl.createSequentialGroup()
                        .addComponent(arg[1])
                        .addComponent(arg[2])
                        .addComponent(arg[3])
                        .addComponent(arg[4]))
        );
        
        gl.linkSize(arg[1], arg[2], arg[3], arg[4]);
        
        pack();
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> 
        {
            var ex = new ListModelsEx();
            ex.setVisible(true);
        });
    }
    
}
