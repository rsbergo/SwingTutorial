package basicswingcomponents2;

import static javax.swing.GroupLayout.Alignment.CENTER;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.Timer;

/**
 * A progress bar is a components that is used when we process lengthy tasks. It is animated so that the user knows that
 * the task is progressing. The JProgressBar component provides a horizontal or a vertical progress bar. The initial and
 * minimum value are 0 and the maximum is 100.
 * 
 * This example displays a progress bar and a button. The button starts and stops the progress.
 */
public class ProgressBarEx extends JFrame
{
    // Data Fields
    private Timer timer;
    private JProgressBar progBar;
    private JButton startBtn;
    private final int MAX_VAL = 100;
    
    // Methods
    /**
     * Constructor.
     */
    public ProgressBarEx()
    {
        initUI();
    }
    
    /**
     * Initialize the UI.
     */
    private void initUI()
    {
        // Create the JProgressBar component. The minimum value is 0, the maximum 100, and the initial value is 0
        // (default values). The setStringPainted() method determines whether the progress bar displays the percentage
        // of the task completed.
        progBar = new JProgressBar();
        progBar.setStringPainted(true);
        
        startBtn = new JButton("Start");
        startBtn.addActionListener(new ClickAction());
        
        // The timer object launches UpdateBarListener every 50ms. Inside the listener, it checks if the progress bar
        // reached its maximum value.
        timer = new Timer(50, new UpdateBarListener());
        
        createLayout(progBar, startBtn);
        
        setTitle("JProgressBar");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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
        
        gl.setHorizontalGroup(gl.createSequentialGroup().addComponent(arg[0]).addComponent(arg[1]));
        
        gl.setVerticalGroup(gl.createParallelGroup(CENTER).addComponent(arg[0]).addComponent(arg[1]));
        
        pack();
    }
    
    /**
     * The actionPerformed() method of the listener increases the current value of the progress bar. If it reaches the
     * maximum value, the timer is stopped and the button's label is set to "End".
     */
    private class UpdateBarListener implements ActionListener
    {
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            int val = progBar.getValue();
            
            if (val >= MAX_VAL)
            {
                timer.stop();
                startBtn.setText("End");
                return;
            }
            
            progBar.setValue(++val);
        }
        
    }
    
    /**
     * The button starts or stops the timer. The text of the button is updated dynamically; it can have "Start", "Stop",
     * or "End" string values.
     */
    private class ClickAction extends AbstractAction
    {
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (timer.isRunning())
            {
                timer.stop();
                startBtn.setText("Start");
            }
            else if (!"End".equals(startBtn.getText()))
            {
                timer.start();
                startBtn.setText("Stop");
            }
        }
        
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var ex = new ProgressBarEx();
            ex.setVisible(true);
        });
    }
    
}
