package basicswingcomponents2;

import static javax.swing.GroupLayout.Alignment.CENTER;
import static javax.swing.LayoutStyle.ComponentPlacement.UNRELATED;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;

/**
 * JToggleButton is a button that has two states: pressed and not pressed. Clicking on it toggles between these two
 * states.
 * 
 * This example has three toggle buttons and a panel. The background color of the display panel is set to black. The
 * toggle buttons will toggle the red, green, and blue parts of the color value. The background color will depend on
 * which toggle buttons have been pressed.
 */
public class ToggleButtonEx extends JFrame implements ActionListener
{
    //Data Fields
    private JToggleButton redBtn;
    private JToggleButton greenBtn;
    private JToggleButton blueBtn;
    private JPanel display;
    
    // Methods
    /**
     * Constructor.
     */
    public ToggleButtonEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        // Create a toggle button and set an action listener to it.
        redBtn = new JToggleButton("red");
        redBtn.addActionListener(this);
        
        greenBtn = new JToggleButton("green");
        greenBtn.addActionListener(this);
        
        blueBtn = new JToggleButton("blue");
        blueBtn.addActionListener(this);
        
        // Create the panel that shows the color value mixed byt toggle buttons. Its preferred size is set, the border
        // line is changed to gray, and set the initial background color.
        display = new JPanel();
        display.setPreferredSize(new Dimension(120, 120));
        display.setBorder(LineBorder.createGrayLineBorder());
        display.setBackground(Color.black);
        
        createLayout(redBtn, greenBtn, blueBtn, display);
        
        setTitle("JToggleButton");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                .addGroup(gl.createParallelGroup()
                        .addComponent(arg[0])
                        .addComponent(arg[1])
                        .addComponent(arg[2]))
                .addPreferredGap(UNRELATED)
                .addComponent(arg[3])
        );
        
        gl.setVerticalGroup(gl.createParallelGroup(CENTER)
                .addGroup(gl.createSequentialGroup()
                        .addComponent(arg[0])
                        .addComponent(arg[1])
                        .addComponent(arg[2]))
                .addComponent(arg[3])
        );
        
        gl.linkSize(redBtn, greenBtn, blueBtn);
        
        pack();
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // Determine the current red, green, and blue parts of the display background color.
        var color = display.getBackground();
        
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        
        // Determine which button was toggled and update the color part of the RGB value accordingly.
        if (e.getActionCommand().equals("red"))
        {
            if (red == 0)
                red = 255;
            else
                red = 0;
        }
        
        if (e.getActionCommand().equals("green"))
        {
            if (green == 0)
                green = 255;
            else
                green = 0;
        }
        
        if (e.getActionCommand().equals("blue"))
        {
            if (blue == 0)
                blue = 255;
            else
                blue = 0;
        }
        
        var setCol = new Color(red, green, blue);
        display.setBackground(setCol);
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> 
        {
            var ex = new ToggleButtonEx();
            ex.setVisible(true);
        });
    }
    
}
