package swinglayoutmanagement;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
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
    private ImageIcon horka1;
    private ImageIcon horka2;
    private ImageIcon horka3;
    private ImageIcon horka4;
    private ImageIcon previ;
    private ImageIcon nexti;
    
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
        // Create the main panel component, set its color to dark gray and put 5px around the panel so that its children
        // are not too close to the border of the window.
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(50, 50, 50));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        // Create the CardLayout manager and set it to the main panel.
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        
        horka1 = new ImageIcon("src/Resources/horka1.jpg");
        horka2 = new ImageIcon("src/Resources/horka2.jpg");
        horka3 = new ImageIcon("src/Resources/horka3.jpg");
        horka4 = new ImageIcon("src/Resources/horka4.jpg");
        
        previ = new ImageIcon("src/Resources/previous.png");
        nexti = new ImageIcon("src/Resources/next.png");
        
        var label1 = new JLabel(horka1);
        var label2 = new JLabel(horka2);
        var label3 = new JLabel(horka3);
        var label4 = new JLabel(horka4);
        
        // Add the label components displaying the images to the panel.
        mainPanel.add(label1);
        mainPanel.add(label2);
        mainPanel.add(label3);
        mainPanel.add(label4);
        
        // Add the main panel to the center area of the border layout of the frame component. If where the component is
        // to be placed is not explicitly specified, it is added to the center area.
        add(mainPanel);
        
        // Create two buttons to navigate through the images. Clicking on the Previous button calls the previous()
        // method of the manager. It flips to the previous card of the specified container.
        var prevButton = new JButton(previ);
        prevButton.addActionListener((e) -> cardLayout.previous(mainPanel));
        
        var nextButton = new JButton(nexti);
        nextButton.addActionListener((e) -> cardLayout.next(mainPanel));
        
        // The buttons are added to the button panel.
        var btnPanel = new JPanel();
        btnPanel.setBackground(new Color(50, 50, 50));
        btnPanel.add(prevButton);
        btnPanel.add(nextButton);
        
        // The panel with the buttons is placed into the south area of the BorderLayout manager.
        add(btnPanel, BorderLayout.SOUTH);
        
        pack();
        
        setTitle("Gallery");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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
