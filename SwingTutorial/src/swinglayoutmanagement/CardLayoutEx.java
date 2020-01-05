package swinglayoutmanagement;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * CardLayout manager treats each component as a card. The container is a stack of these cards. Only one component is
 * visible at a time, the rest is hidden. The first component added to the container is visible by default when the
 * container is initially displayed. This layout manager can be used to create a wizard or a tabbed pane.
 * 
 * This example uses a CardLayout to create a gallery of images. It uses four images of the Krasna Horka castle (before
 * the fire in 2012).
 */
public class CardLayoutEx extends JFrame
{
    // Data Fields
    private JPanel mainPanel;
    private CardLayout cardLayout;
    
    // Methods
    /**
     * Constructor.
     */
    public CardLayoutEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        // Add the main panel to the center area of the border layout of the frame component. If where the component is
        // to be placed is not explicitly specified, it is added to the center area.
        add(createMainPanel());
        
        // The panel with the buttons is placed into the south area of the BorderLayout manager.
        add(createButtonPanel(), BorderLayout.SOUTH);
        
        pack();
        
        setTitle("Gallery");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    /**
     * Create the button panel. Create two buttons to navigate through the images. Clicking on the Previous button calls
     * the previous() method of the manager. It flips to the previous card of the specified container.
     */
    private JPanel createButtonPanel()
    {
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(50, 50, 50));
        btnPanel.add(createButtonForButtonPanel(new ImageIcon("src/Resources/previous.png"), (e) -> cardLayout.previous(mainPanel)));
        btnPanel.add(createButtonForButtonPanel(new ImageIcon("src/Resources/next.png"), (e) -> cardLayout.next(mainPanel)));
        return btnPanel;
    }
    
    /**
     * Create the a button for the Button Panel.
     */
    private JButton createButtonForButtonPanel(ImageIcon icon, ActionListener aListener)
    {
        JButton previousBtn = new JButton(icon);
        if (aListener != null)
            previousBtn.addActionListener(aListener);
        return previousBtn;
    }
    
    /**
     * Create a list of labels containing the images to be displayed.
     */
    private ArrayList<JLabel> createImageLabels()
    {
        ArrayList<JLabel> images = new ArrayList<JLabel>();
        images.add(new JLabel(new ImageIcon("src/Resources/horka1.jpg")));
        images.add(new JLabel(new ImageIcon("src/Resources/horka2.jpg")));
        images.add(new JLabel(new ImageIcon("src/Resources/horka3.jpg")));
        images.add(new JLabel(new ImageIcon("src/Resources/horka4.jpg")));
        return images;
    }
    
    /**
     * Create the main panel.
     */
    private JPanel createMainPanel()
    {
        mainPanel = new JPanel();
        
        // Create the main panel component, set its color to dark gray and put 5px around the panel so that its children
        // are not too close to the border of the window.
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(50, 50, 50));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        // Create the CardLayout manager and set it to the main panel.
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        
        // Add the label components displaying the images to the panel.
        for (JLabel label : createImageLabels())
            mainPanel.add(label);
        
        return mainPanel;
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new CardLayoutEx();
            ex.setVisible(true);
        });
    }
    
}
