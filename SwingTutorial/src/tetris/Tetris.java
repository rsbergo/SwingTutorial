package tetris;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * The Tetris game is one of the most popular computer games ever created. The original game was designed and programmed
 * by a Russian programmer Alexey Pajitmov in 1985. Since then, Tetris is available on almoes every computer platform in
 * lots of variation.
 * 
 * Tetris is called a falling block puzzle game. In this game, we have seven different shapes called tetrominoes:
 * S-shape, Z-shape, T-shape, L-shape, Line-shape, MirroredL-shape, and a Square-shape. Each of these shapes is formed
 * with four squares. The shapes are falling down the board. The objective of the Tetris game is to move and rotate the
 * shapes so that they fit as much as possible. If a row is formed, the row is destroyed and the player scores. The game
 * is played until the player tops out.
 * 
 * There are no images for the Tetris game, the tetrominoes are drawn using Swing drawing API. Behind every computer
 * game, there is a mathematical model; so it is in Tetris.
 * 
 * Some ideas behind the game:
 * 
 *     - Use a Timer class to create a game cycle 
 *     - The tetrominoes are drawn
 *     - The shapes move on a square by square basis
 *     - Mathematically, a board is a simple list of numbers
 * 
 * The game is simplified so that it is easier to understand. The game starts immediately after it is launched. The game 
 * can be paused by pressing the p key. The space key drops the Tetris piece to the bottom. The d key drops the piece 
 * one line down (it can be used to speed up the falling a bit). The game goes at constant speed, no acceleration is 
 * implemented. The score is the number of lines that have been removed.
 * 
 * Originally written by Jan Bodnar.
 * 
 * Sets up the game: create a board on which the game is played and create a statusbar.
 */
public class Tetris extends JFrame
{
    // Data Fields
    private JLabel statusbar;
    
    // Methods
    public Tetris()
    {
        initUI();
    }
    
    private void initUI()
    {
        statusbar = new JLabel(" 0");
        add(statusbar, BorderLayout.SOUTH);
        
        var board = new Board(this);
        add(board);
        
        // Starts the Tetris game.
        board.start();
        
        setTitle("Tetris");
        setSize(200, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    JLabel getStatusBar()
    {
        return statusbar;
    }
    
    // Driver
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> 
        {
            var game = new Tetris();
            game.setVisible(true);
        });
    }
    
}
