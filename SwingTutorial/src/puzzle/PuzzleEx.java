package puzzle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class MyButton extends JButton
{
    // Data Fields
    private boolean isLastButton;
    
    // Methods
    public MyButton()
    {
        super();
        initUI();
    }
    
    public MyButton(Image image)
    {
        super(new ImageIcon(image));
        initUI();
    }
    
    private void initUI()
    {
        isLastButton = false;
        BorderFactory.createLineBorder(Color.gray);
        
        // When a mouse is hovered over the button, its border changes to yellow color.
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                setBorder(BorderFactory.createLineBorder(Color.yellow));
            }
            
            @Override
            public void mouseExited(MouseEvent e)
            {
                setBorder(BorderFactory.createLineBorder(Color.gray));
            }
        });
    }
    
    public void setLastButton()
    {
        isLastButton = true;
    }
    
    /**
     * There is one button called last button. It is a button that does not have an image. Other buttons swap space with
     * this one.
     */
    public boolean isLastButton()
    {
        return isLastButton;
    }
}

/**
 * The goal for this game is to form a picture. Buttons containing images are moved by clicking on them. Only buttons
 * adjacent to the empty button can be moved.
 * 
 * An image of Sid character from the Ice Age movie is used. The image is scaled and cut into 12 pieces. These pieces
 * are used by JButton components. The last piece is not used; an empty button is used instead. Different, large
 * pictures can be used in this game.
 */
public class PuzzleEx extends JFrame
{
    // Data Fields
    private JPanel panel;
    private BufferedImage source;
    private BufferedImage resized;
    private Image image;
    private MyButton lastButton;
    private int width, height;
    
    private List<MyButton> buttons;
    private List<Point> solution;
    
    private final int NUMBER_OF_BUTTONS = 12;
    
    // The image used to form the puzzle is scaled to have the desired width. The getNewHeight() method calculates the
    // new height, keeping the image's ratio.
    private final int DESIRED_WIDTH = 300;
    
    // Methods
    public PuzzleEx()
    {
        initUI();
    }
    
    private void initUI()
    {
        solution = new ArrayList<>();
        
        // The solution array list stores the correct order of buttons that form the image. Each button is identified by
        // one Point.
        solution.add(new Point(0, 0));
        solution.add(new Point(0, 1));
        solution.add(new Point(0, 2));
        solution.add(new Point(1, 0));
        solution.add(new Point(1, 1));
        solution.add(new Point(1, 2));
        solution.add(new Point(2, 0));
        solution.add(new Point(2, 1));
        solution.add(new Point(2, 2));
        solution.add(new Point(3, 0));
        solution.add(new Point(3, 1));
        solution.add(new Point(3, 2));
        
        buttons = new ArrayList<>();
        
        panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.gray));
        
        // Use a GridLayout to store the components. The layout consists of 4 rows and 3 columns.
        panel.setLayout(new GridLayout(4, 3, 0, 0));
        
        try
        {
            source = loadImage();
            int h = getNewHeight(source.getWidth(), source.getHeight());
            resized = resizeImage(source, DESIRED_WIDTH, h, BufferedImage.TYPE_INT_ARGB);
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(this, "Could not load image", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        width = resized.getWidth(null);
        height = resized.getHeight(null);
        
        add(panel, BorderLayout.CENTER);
        
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                // CropImageFilter is used to cut a rectangular shape from the already resized image source. It is meant
                // to be used in conjunction with a FilteredImageSource object to produce cropped versions of existing
                // images.
                image = createImage(new FilteredImageSource(resized.getSource(), 
                                    new CropImageFilter(j * width / 3, i * height / 4, width / 3, height / 4)));
                var button = new MyButton(image);
                
                // Buttons are identified by their position client property. It is a point containing the button's
                // correct row and column position in the picture. These properties are used to find out if the buttons
                // are in the correct order in the window.
                button.putClientProperty("position", new Point(i, j));
                
                // The button with no image is called the last button and is placed at the end of the grid, in the
                // bottom-right corner. It is the button that swaps its position with the adjacent button that is being
                // clicked. Its isLastButton flag is set with the setLastButton() method.
                if (i == 3 && j == 2)
                {
                    lastButton = new MyButton();
                    lastButton.setBorderPainted(false);
                    lastButton.setContentAreaFilled(false);
                    lastButton.setLastButton();
                    lastButton.putClientProperty("position", new Point(i, j));
                }
                else
                    buttons.add(button);
            }
        }
        
        // Randomly reorder the elements of the buttons list. The last button (the button with no image) is inserted at
        // the end of the list. It is not supposed to be shuffled, it always goes at the end when the puzzle is started.
        Collections.shuffle(buttons);
        buttons.add(lastButton);
        
        // All the components of the buttons list are placed on the panel. Create some gray border around the buttons
        // and add a click action listener.
        for (int i = 0; i < NUMBER_OF_BUTTONS; i++)
        {
            var btn = buttons.get(i);
            panel.add(btn);
            btn.setBorder(BorderFactory.createLineBorder(Color.gray));
            btn.addActionListener(new ClickAction());
        }
        
        pack();
        
        setTitle("Puzzle");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    /**
     * Calculates the height of the image based on the desired width. The image's ratio is kept. The image is scaled
     * using these values.
     */
    private int getNewHeight(int w, int h)
    {
        double ratio = DESIRED_WIDTH / (double) w;
        int newHeight = (int) (h * ratio);
        return newHeight;
    }
    
    /**
     * A JPG image is loaded from the disk. ImageIO's read() method returns a BufferedImage, which is Swing's important
     * class for manipulating images.
     */
    private BufferedImage loadImage() throws IOException
    {
        var bimg = ImageIO.read(new File("src/Resources/puzzle/icesid.jpg"));
        return bimg;
    }
    
    /**
     * The original image is resized by creating a new BufferedImage with new dimensions. It paints from the original
     * image into this new buffered image.
     */
    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height, int type)
    {
        var resizedImage = new BufferedImage(width, height, type);
        var g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        
        return resizedImage;
    }
    
    /**
     * Buttons are stored in an array list. This list is then mapped to the grid of the panel. The indexes of the last
     * button and the clicked button are retrieved. They are swapped using the Collections.swap() if they are adjacent.
     */
    private class ClickAction extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            checkButton(e);
            checkSolution();
        }
        
        private void checkButton(ActionEvent e)
        {
            int lidx = 0;
            
            for (MyButton button : buttons)
            {
                if (button.isLastButton())
                    lidx = buttons.indexOf(button);
            }
            
            var button = (JButton) e.getSource();
            int bidx = buttons.indexOf(button);
            
            if ((bidx - 1 == lidx) || (bidx + 1 == lidx) || (bidx - 3 == lidx) || (bidx + 3 == lidx))
            {
                Collections.swap(buttons, bidx, lidx);
                updateButtons();
            }
        }
        
        /**
         * Maps the list to the panel's grid. First, all components are removed with the removeAll() method. A for loop
         * is used to go through the buttons list to add the reordered buttons back to the panel's layout manager.
         * Finally, the validate() method implements the new layout.
         */
        private void updateButtons()
        {
            panel.removeAll();
            for (JComponent btn : buttons)
                panel.add(btn);
            
            panel.validate();
        }
    }
    
    /**
     * Solution checking is done by comparing the list of points of the correctly ordered buttons with the current list
     * containing the order of buttons from the window. A message dialog is shown in case the solution is reached.
     */
    private void checkSolution()
    {
        var current = new ArrayList<Point>();
        
        for (JComponent btn : buttons)
            current.add((Point) btn.getClientProperty("position"));
        
        if (compareList(solution, current))
            JOptionPane.showMessageDialog(panel, "Finished", "Congratulation", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static boolean compareList(List ls1, List ls2)
    {
        return ls1.toString().contentEquals(ls2.toString());
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var puzzle = new PuzzleEx();
            puzzle.setVisible(true);
        });
    }
    
}
