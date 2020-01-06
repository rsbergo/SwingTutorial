package swinglayoutmanagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextPane;

/**
 * This example creates a Tip of the Day window dialog using a combination of various layout managers.
 * 
 * This example uses a mix of layout managers, simply putting four panels into the vertically organized basic panel.
 */
public class TipOfDayEx extends JDialog
{
    /**
     * Constructor.
     */
    public TipOfDayEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        // The very bottom panel. It has a vertical layout manager. The basic panel is added to the default JDialog
        // component. This component has a border layout manager by default.
        var basePanel = new JPanel();
        basePanel.setLayout(new BoxLayout(basePanel, BoxLayout.Y_AXIS));
        add(basePanel);
        
        // The top panel has a border layout manager. Three components are put into it: two labels and a separator. To
        // have a panel that is not greater than its components, the maximum size must be set (the manager calculates
        // the necessary heights.
        var topPanel = new JPanel(new BorderLayout(0, 0));
        topPanel.setMaximumSize(new Dimension(450, 0));
        var hint = new JLabel("Productivity Hints");
        hint.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        topPanel.add(hint);
        var icon = new ImageIcon("src/Resources/coffee2.png");
        var label = new JLabel(icon);
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        topPanel.add(label, BorderLayout.EAST);
        var separator = new JSeparator();
        separator.setForeground(Color.gray);
        topPanel.add(separator, BorderLayout.SOUTH);
        basePanel.add(topPanel);
        
        // The text component is added to the center area of the border layout manager. It takes all space left.
        var textPanel = new JPanel(new BorderLayout());
        textPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
        var pane = new JTextPane();
        pane.setContentType("text/html");
        var text = "<p><b>Closing windows using the mouse wheel</b></p>"
                + "<p>Clicking with the mouse wheel on an editor tab closes the window. "
                + "This method works also with dockable windows or Log window tabs.</p>";
        pane.setText(text);
        pane.setEditable(false);
        textPanel.add(pane);
        basePanel.add(textPanel);
        
        // The check box is shown in the box panel. It ss left aligned. The flow layout manager has a 20px horizontal
        // gap. Other components have 25px. This is because the flow layout manager puts some space between the
        // component and the edge as well.
        var boxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        var box = new JCheckBox("Show Tips at startup");
        box.setMnemonic(KeyEvent.VK_S);
        boxPanel.add(box);
        basePanel.add(boxPanel);
        
        // The bottom panel displays two buttons. It has a right aligned flow manager. In order to show the buttons on
        // the right edge of the dialog, the panel must stretch horizontally from the beginning to the end.
        var bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        var tipBtn = new JButton("Next Tip");
        tipBtn.setMnemonic(KeyEvent.VK_N);
        var closeBtn = new JButton("Close");
        closeBtn.setMnemonic(KeyEvent.VK_C);
        bottomPanel.add(tipBtn);
        bottomPanel.add(closeBtn);
        basePanel.add(bottomPanel);
        bottomPanel.setMaximumSize(new Dimension(450, 0));
        
        setTitle("Tip of the Day");
        setSize(new Dimension(450, 350));
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new TipOfDayEx();
            ex.setVisible(true);
        });
    }
    
}
