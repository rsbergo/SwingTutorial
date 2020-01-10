package swingdialogs;

import static javax.swing.GroupLayout.DEFAULT_SIZE;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * JFileChooser is a standard dialog for selecting a file from the file system.
 * 
 * This example demonstrates how to use a JFileChooser to load a file contents into the text area component.
 */
public class FileChooserEx extends JFrame
{
    // Data Fields
    private JPanel panel;
    private JTextArea area;
    
    // Methods
    /**
     * Constructor
     */
    public FileChooserEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        panel = (JPanel) getContentPane();
        area = new JTextArea();
        
        var spane = new JScrollPane();
        spane.getViewport().add(area);
        
        var toolbar = createToolBar();
        
        createLayout(toolbar, spane);
        
        setTitle("JFileChooser");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * Create the toolbar.
     */
    private JToolBar createToolBar()
    {
        var openIcon = new ImageIcon("src/Resources/swingdialogs/document-open.png");
        
        var toolbar = new JToolBar();
        var openBtn = new JButton(openIcon);
        
        openBtn.addActionListener(new OpenFileAction());
        
        toolbar.add(openBtn);
        
        return toolbar;
    }
    
    /**
     * Create the layout.
     */
    private void createLayout(JComponent... arg)
    {
        var pane = getContentPane();
        var gl = new GroupLayout(panel);
        pane.setLayout(gl);
        
        gl.setHorizontalGroup(gl.createParallelGroup()
                .addComponent(arg[0], DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(gl.createSequentialGroup()
                        .addComponent(arg[1]))
        );
        
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
                .addGap(4)
                .addComponent(arg[1])
        );
        
        pack();
    }
    
    /**
     * Read the given file.
     */
    public String readFile(File file)
    {
        String content = "";
        
        try
        {
            content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(this, "Could not read file", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return content;
    }
    
    private class OpenFileAction extends AbstractAction
    {
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // Create the file chooser dialog.
            var fileChooser = new JFileChooser();
            
            // Define the file filter. In this case, this defines the file filter for Java files with extension .java.
            // There is also the default All files option.
            var filter = new FileNameExtensionFilter("Java files", "java");
            fileChooser.addChoosableFileFilter(filter);
            
            // The showDialog() method displays the dialog on the screen. The JFileChooser.APPROVE_OPTION is returned
            // when the Yes or OK buttons are clicked.
            int ret = fileChooser.showDialog(panel, "Open file");
            
            // Get the name of the selected file, read the contents of the file and set the text into the text area.
            if (ret == JFileChooser.APPROVE_OPTION)
            {
                var file = fileChooser.getSelectedFile();
                var text = readFile(file);
                
                area.setText(text);
            }
        }
        
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new FileChooserEx();
            ex.setVisible(true);
        });
    }
    
}
