package basicswingcomponents;

import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

/**
 * JTextField is a text component that allows editing of a single line of non-formatted text.
 * 
 * In this example, the text entered into the JTextField is shown immediately in a label component.
 */
public class JTextFieldEx extends JFrame
{
    // Data Fields
    private JLabel lbl;
    
    // Methods
    /**
     * Constructor.
     */
    public JTextFieldEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        // A new JTextField is created, the parameter is the number of columns. This value does not set the number of
        // characters allowed in the field; it is used to calculate the preferred width of the field.
        var field = new JTextField(15);
        lbl = new JLabel();
        
        // A document listener is added to the JTextField. The getDocument() method fetches the model associated with
        // the editor. Each Swing component has a model, which manages its state or data.
        field.getDocument().addDocumentListener(new MyDocumentListener());
        
        createLayout(field, lbl);
        
        setTitle("JTextField");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * The MyDocumentListener inner class.
     * 
     * The insertUpdate() and removeUpdate() methods call the updateLabel() method, which copies the text from the text
     * field and sets it into the label component. The changeUpdate() method is generated in styled documents only.
     */
    private class MyDocumentListener implements DocumentListener
    {
        private String text;
        
        @Override
        public void insertUpdate(DocumentEvent e)
        {
            updateLabel(e);
        }
        
        @Override
        public void removeUpdate(DocumentEvent e)
        {
            updateLabel(e);
        }
        
        @Override
        public void changedUpdate(DocumentEvent e)
        {}
        
        /**
         * The document event's getDocument() method is used to get the document of the text field being observed. We
         * get the number of characters using the document's getLength() method. The value is used to copy the text with
         * the document's getText() method. Finally, the text is set to the label with the label's setText() method.
         */
        private void updateLabel(DocumentEvent e)
        {
            var doc = e.getDocument();
            int len = doc.getLength();
            
            try
            {
                text = doc.getText(0, len);
            }
            catch (BadLocationException ex)
            {
                Logger.getLogger(JTextFieldEx.class.getName()).log(Level.WARNING, "Bad location", ex);
            }
            
            lbl.setText(text);
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
        
        gl.setHorizontalGroup(gl.createParallelGroup()
                .addComponent(arg[0])
                .addComponent(arg[1])
                .addGap(250)
        );
        
        // The JTextField is not intended to grow vertically, so its maximum value is set to GroupLayout.PREFERRED_SIZE
        // in the vertical direction.
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(arg[1])
                .addGap(150)
        );
        
        pack();
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new JTextFieldEx();
            ex.setVisible(true);
        });
    }
    
}
