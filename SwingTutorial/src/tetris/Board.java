package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import tetris.Shape.Tetrominoe;

/**
 * Contains the game logic.
 */
public class Board extends JPanel implements ActionListener
{
    // Define the size of the board.
    private final int BOARD_WIDTH = 10;
    private final int BOARD_HEIGHT = 22;
    
    // Initialize important variables.
    private Timer timer;
    private boolean isFallingFinished = false; // Determines if the shape has finished falling and need to create a new.
    private boolean isStarted = false;
    private boolean isPaused = false;
    private int numLinesRemoved = 0;           // Counts the number of lines that have been removed so far.
    private int curX = 0;                      // Determine the actual position of the falling Tetris shape.
    private int curY = 0;
    private JLabel statusbar;
    private Shape curPiece;
    private Tetrominoe[] board;
    
    public Board(Tetris parent)
    {
        initBoard(parent);
    }
    
    private void initBoard(Tetris parent)
    {
        // setFocusable() method must be explicitly called. From now, the board has the keyboard input.
        setFocusable(true);
        curPiece = new Shape();
        
        // Defines the speed of the game. The Timer object fires one or more action events after a specified delay. In
        // this case, the Timer calls the actionPerformed() method each DELAY ms.
        int DELAY = 400;
        timer = new Timer(DELAY, this);
        timer.start();
        
        statusbar = parent.getStatusBar();
        board = new Tetrominoe[BOARD_WIDTH * BOARD_HEIGHT];
        addKeyListener(new TAdapter());
        clearBoard();
    }
    
    /**
     * Checks if the falling has finished. If so, a new piece is created. If not, the falling Tetris piece goes one line
     * down.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (isFallingFinished)
        {
            isFallingFinished = false;
            newPiece();
        }
        else
            oneLineDown();
    }
    
    private int squareWidth() { return (int) getSize().getWidth() / BOARD_WIDTH; }
    private int squareHeight() { return (int) getSize().getHeight() / BOARD_HEIGHT; }
    private Tetrominoe shapeAt(int x, int y) { return board[(y * BOARD_WIDTH) + x]; }
    
    void start()
    {
        if (isPaused)
            return;
        
        isStarted = true;
        isFallingFinished = false;
        numLinesRemoved = 0;
        clearBoard();
        
        newPiece();
        timer.start();
    }
    
    private void pause()
    {
        if (!isStarted)
            return;
        
        isPaused = !isPaused;
        
        if (isPaused)
        {
            timer.stop();
            statusbar.setText("paused");
        }
        else
        {
            timer.start();
            statusbar.setText(String.valueOf(numLinesRemoved));
        }
        
        repaint();
    }
    
    /**
     * Draws all objects on the board.
     */
    private void doDrawing(Graphics g)
    {
        var size = getSize();
        int boardTop = (int) size.getHeight() - BOARD_HEIGHT * squareHeight();
        
        // Paint all the shapes or remains of the shapes that have been dropped to the bottom of the board. All the
        // squares are remembered in the board array. Access to it is done through the shapeAt() method.
        for (int i = 0; i < BOARD_HEIGHT; ++i)
        {
            for (int j = 0; j < BOARD_WIDTH; ++j)
            {
                Tetrominoe shape = shapeAt(j, BOARD_HEIGHT - i - 1);
                
                if (shape != Tetrominoe.NoShape)
                    drawSquare(g, j * squareWidth(), boardTop + i * squareHeight(), shape);
            }
        }
        
        // Paint the actual falling piece.
        if (curPiece.getShape() != Tetrominoe.NoShape)
        {
            for (int i = 0; i < 4; ++i)
            {
                int x = curX + curPiece.x(i);
                int y = curY - curPiece.y(i);
                
                drawSquare(g, x * squareWidth(), boardTop + (BOARD_HEIGHT - y - 1) * squareHeight(), curPiece.getShape());
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        doDrawing(g);
    }
    
    /**
     * If the Space key is pressed, the piece is dropped to the bottom. Try to drop the piece one line down until it
     * reaches the bottom or the top of another fallen Tetris piece. When the Tetris piece finishes falling, the
     * pieceDropped() is called.
     */
    private void dropDown()
    {
        int newY = curY;
        
        while (newY > 0)
        {
            if (!tryMove(curPiece, curX, newY - 1))
                break;
            
            --newY;
        }
        
        pieceDropped();
    }
    
    private void oneLineDown()
    {
        if (!tryMove(curPiece, curX, curY - 1))
            pieceDropped();
    }
    
    /**
     * Fills the board with empty NoShapes.
     */
    private void clearBoard()
    {
        for (int i = 0; i < BOARD_HEIGHT * BOARD_WIDTH; ++i)
            board[i] = Tetrominoe.NoShape;
    }
    
    /**
     * Puts the falling piece into the board array. The board holds all the squares of the pieces and remains of the
     * pieces that have finished falling. When the piece has finished falling, it is time to check if we can remove some
     * lines off the board through the removeFullLines() method. Then, try to create a new piece.
     */
    private void pieceDropped()
    {
        for (int i = 0; i < 4; ++i)
        {
            int x = curX + curPiece.x(i);
            int y = curY - curPiece.y(i);
            board[(y * BOARD_WIDTH) + x] = curPiece.getShape();
        }
        
        removeFullLines();
        
        if (!isFallingFinished)
            newPiece();
    }
    
    /**
     * Creates a new Tetris piece. The piece gets a new random shape. Then, compute the initial curX and curY values. If
     * the piece cannot move to the initial positions, the game is over, the played tops out. The timer is stopped and
     * the "game over" string is put on the statusbar.
     */
    private void newPiece()
    {
        curPiece.setRandomShape();
        curX = BOARD_WIDTH / 2 + 1;
        curY = BOARD_HEIGHT - 1 + curPiece.minY();
        
        if (!tryMove(curPiece, curX, curY))
        {
            curPiece.setShape(Tetrominoe.NoShape);
            timer.stop();
            isStarted = false;
            statusbar.setText("game over");
        }
    }
    
    /**
     * Tries to move the Tetris piece. The method returns false if it has reached the board boundaries or if is adjacent
     * to the already fallen Tetris pieces.
     */
    private boolean tryMove(Shape newPiece, int newX, int newY)
    {
        for (int i = 0; i < 4; ++i)
        {
            int x = newX + newPiece.x(i);
            int y = newY - newPiece.y(i);
            
            if (x < 0 || x >= BOARD_WIDTH || y < 0 || y >= BOARD_HEIGHT)
                return false;
            
            if (shapeAt(x, y) != Tetrominoe.NoShape)
                return false;
        }
        
        curPiece = newPiece;
        curX = newX;
        curY = newY;
        
        repaint();
        
        return true;
    }
    
    /**
     * Check if there are any full row among all rows in the board. If there is at least one full line, it is removed.
     * After finding a full line, increase the counter. Move all the lines above the full row one line down in order to
     * destroy the full line. This game uses naive gravity, i.e. the squares may be left floating above empty gaps.
     */
    private void removeFullLines()
    {
        int numFullLines = 0;
        
        for (int i = BOARD_HEIGHT - 1; i >= 0; --i)
        {
            boolean lineIsFull = true;
            
            for (int j = 0; j < BOARD_WIDTH; ++j)
            {
                if (shapeAt(j, i) == Tetrominoe.NoShape)
                {
                    lineIsFull = false;
                    break;
                }
            }
            
            if (lineIsFull)
            {
                ++numFullLines;
                
                for (int k = i; k < BOARD_HEIGHT - 1; ++k)
                {
                    for (int j = 0; j < BOARD_WIDTH; ++j)
                        board[(k * BOARD_WIDTH) + j] = shapeAt(j, k + 1);
                }
            }
        }
        
        if (numFullLines > 0)
        {
            numLinesRemoved += numFullLines;
            statusbar.setText(String.valueOf(numLinesRemoved));
            isFallingFinished = true;
            curPiece.setShape(Tetrominoe.NoShape);
            repaint();
        }
    }
    
    /**
     * Each Tetris piece has four squares, each square is drawn with the drawSquare() method. Tetris pieces have
     * different colors. To simulate a 3D edge, the left and top sides of a square are drawn with a brighter color; the
     * bottom and right sides are drawn with darker colors.
     */
    private void drawSquare(Graphics g, int x, int y, Tetrominoe shape)
    {
        Color colors[] = { new Color(  0,   0,   0), new Color(204, 102, 102), new Color(102, 204, 102),
                           new Color(102, 102, 204), new Color(204, 204, 102), new Color(204, 102, 204), 
                           new Color(102, 204, 204), new Color(218, 170,   0) };
        
        var color = colors[shape.ordinal()];
        
        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);
        
        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight() - 1, x, y);
        g.drawLine(x, y, x + squareWidth() - 1, y);
        
        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight() - 1, x + squareWidth() - 1, y + squareHeight() - 1);
        g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1, x + squareWidth() - 1, y + 1);
    }
    
    /**
     * The game is controlled with a keyboard. The control mechanism is implemented with a KeyAdapter. If, for example,
     * the left key is pressed, the falling piece is attempted to be moved to the left.
     */
    class TAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            if (!isStarted || curPiece.getShape() == Tetrominoe.NoShape)
                return;
            
            int keycode = e.getKeyCode();
            
            if (keycode == 'P')
            {
                pause();
                return;
            }
            
            if (isPaused)
                return;
            
            switch (keycode)
            {
                case KeyEvent.VK_LEFT:
                    tryMove(curPiece, curX - 1, curY);
                    break;
                
                case KeyEvent.VK_RIGHT:
                    tryMove(curPiece, curX + 1, curY);
                    break;
                
                case KeyEvent.VK_DOWN:
                    tryMove(curPiece.rotateRight(), curX, curY);
                    break;
                
                case KeyEvent.VK_UP:
                    tryMove(curPiece.rotateLeft(), curX, curY);
                    break;
                    
                case KeyEvent.VK_SPACE:
                    dropDown();
                    break;
                    
                case KeyEvent.VK_D:
                    oneLineDown();
                    break;
            }
        }
    }
}
