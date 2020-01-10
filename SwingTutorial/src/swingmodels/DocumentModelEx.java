package swingmodels;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * A document model is a good example of a separation of a data from the visual representation. In a JTextPane
 * component, a StyleDocument is used for setting the style of the text data.
 * 
 * This example contains a text pane and a toolbar. In the toolbar, there are four buttons that change the attributes of
 * the text.
 */
public class DocumentModelEx extends JFrame
{
    // Data Fields
    private StyledDocument sdoc;
    private JTextPane textPane;
    
    // Methods
    /**
     * Constructor.
     */
    public DocumentModelEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        createToolbar();
        
        var panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        
        textPane = new JTextPane();
        
        // Get the styled document, which is a model for the text pane component.
        sdoc = textPane.getStyledDocument();
        initStyles(textPane);
        
        panel.add(new JScrollPane(textPane));
        add(panel);
        pack();
        
        setTitle("Document Model");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * Create the toolbar.
     */
    private void createToolbar()
    {
        var toolbar = new JToolBar();
        
        var bold = new ImageIcon("src/Resources/swingmodels/bold.png");
        var italic = new ImageIcon("src/Resources/swingmodels/italic.png");
        var strike = new ImageIcon("src/Resources/swingmodels/strike.png");
        var underline = new ImageIcon("src/Resources/swingmodels/underline.png");
        
        var boldBtn = new JButton(bold);
        var italBtn = new JButton(italic);
        var striBtn = new JButton(strike);
        var undeBtn = new JButton(underline);
        
        toolbar.add(boldBtn);
        toolbar.add(italBtn);
        toolbar.add(striBtn);
        toolbar.add(undeBtn);
        
        add(toolbar, BorderLayout.NORTH);
        
        // Change the attributes of the text. The parameters are the offset and length of the selection, the style and
        // the boolean value replace. The offset is the beginning of the text where the bold text is applied. The length
        // is retrieved by subtracting the selection end and selection start values. Boolean value false means that an
        // old style is not replaced with a new one, but merged. This means that if the text is underlined and made
        // bold, the result is an underlined bold text.
        boldBtn.addActionListener(e -> sdoc.setCharacterAttributes(
                textPane.getSelectionStart(), 
                textPane.getSelectionEnd() - textPane.getSelectionStart(), 
                textPane.getStyle("Bold"), 
                false)
        );
        
        italBtn.addActionListener(e -> sdoc.setCharacterAttributes(
                textPane.getSelectionStart(), 
                textPane.getSelectionEnd() - textPane.getSelectionStart(), 
                textPane.getStyle("Italic"), 
                false)
        );
        
        striBtn.addActionListener(e -> sdoc.setCharacterAttributes(
                textPane.getSelectionStart(), 
                textPane.getSelectionEnd() - textPane.getSelectionStart(), 
                textPane.getStyle("Strike"), 
                false)
        );
        
        undeBtn.addActionListener(e -> sdoc.setCharacterAttributes(
                textPane.getSelectionStart(), 
                textPane.getSelectionEnd() - textPane.getSelectionStart(), 
                textPane.getStyle("Underline"), 
                false)
        );
    }
    
    /**
     * Initialize the styles.
     */
    private void initStyles(JTextPane textPane)
    {
        // A style is a set of text attributes, such as color and size. Register a bold style for the text pane
        // component. The registered styles can be retrieved at any time.
        var style = textPane.addStyle("Bold", null);
        StyleConstants.setBold(style, true);
        
        style = textPane.addStyle("Italic", null);
        StyleConstants.setItalic(style, true);
        
        style = textPane.addStyle("Underline", null);
        StyleConstants.setUnderline(style, true);
        
        style = textPane.addStyle("Strike", null);
        StyleConstants.setStrikeThrough(style, true);
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> 
        {
            var ex = new DocumentModelEx();
            ex.setVisible(true);
        });
    }
    
}
