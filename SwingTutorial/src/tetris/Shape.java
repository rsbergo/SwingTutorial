package tetris;

import java.util.Random;

/**
 * Provides information about a Tetris piece.
 */
public class Shape
{
    // Holds seven Tetris shape names and the empty shape called NoShape.
    protected enum Tetrominoe
    {
        NoShape, ZShape, SShape, LineShape, TShape, SquareShape, LShape, MirroredLShape
    }
    
    private Tetrominoe pieceShape;
    private int coords[][];
    private int[][][] coordsTable;
    
    /**
     * Constructor of the Shape class. The coords array holds the actual coordinates of a Tetris piece.
     */
    public Shape()
    {
        coords = new int[4][2];
        setShape(Tetrominoe.NoShape);
    }
    
    void setShape(Tetrominoe shape)
    {
        // Holds all possible coordinate values of the Tetris pieces. This is a template from which all pieces take
        // their coordinate values.
        coordsTable = new int[][][] { { {  0,  0 }, { 0,  0 }, {  0, 0 }, {  0, 0 } },
                                      { {  0, -1 }, { 0,  0 }, { -1, 0 }, { -1, 1 } }, 
                                      { {  0, -1 }, { 0,  0 }, {  1, 0 }, {  1, 1 } },
                                      { {  0, -1 }, { 0,  0 }, {  0, 1 }, {  0, 2 } }, 
                                      { { -1,  0 }, { 0,  0 }, {  1, 0 }, {  0, 1 } },
                                      { {  0,  0 }, { 1,  0 }, {  0, 1 }, {  1, 1 } }, 
                                      { { -1, -1 }, { 0, -1 }, {  0, 0 }, {  0, 1 } },
                                      { {  1, -1 }, { 0, -1 }, {  0, 0 }, {  0, 1 } } 
                                    };
        
        // Copy one row of the coordinate values from the coordsTable to a coords array of a Tetris piece. Note the use
        // of the ordinal() method. Java enums are full classes and the ordinal() method returns the current position of
        // the enum type in the enum object. The coords array saves the coordinates of the Tetris piece.
        for (int i = 0; i < 4; i++)
            System.arraycopy(coordsTable[shape.ordinal()], 0, coords, 0, 4);
        
        pieceShape = shape;
    }
    
    private void setX(int index, int x)
    {
        coords[index][0] = x;
    }
    
    private void setY(int index, int y)
    {
        coords[index][1] = y;
    }
    
    int x(int index)
    {
        return coords[index][0];
    }
    
    int y(int index)
    {
        return coords[index][1];
    }
    
    Tetrominoe getShape()
    {
        return pieceShape;
    }
    
    void setRandomShape()
    {
        var r = new Random();
        int x = Math.abs(r.nextInt()) % 7 + 1;
        
        Tetrominoe[] values = Tetrominoe.values();
        setShape(values[x]);
    }
    
    public int minX()
    {
        int m = coords[0][0];
        
        for (int i = 0; i < 4; i++)
            m = Math.min(m, coords[i][0]);
        
        return m;
    }
    
    int minY()
    {
        int m = coords[0][1];
        
        for (int i = 0; i < 4; i++)
            m = Math.min(m, coords[i][1]);
        
        return m;
    }
    
    Shape rotateLeft()
    {
        if (pieceShape == Tetrominoe.SquareShape)
            return this;
        
        var result = new Shape();
        result.pieceShape = pieceShape;
        
        for (int i = 0; i < 4; i++)
        {
            result.setX(i, y(i));
            result.setY(i, -x(i));
        }
        
        return result;
    }
    
    /**
     * Rotates the piece to the right. The square does not have to be rotated, so simply return the reference to the
     * current object in case of Tetrominoe.SquareShape.
     */
    Shape rotateRight()
    {
        if (pieceShape == Tetrominoe.SquareShape)
            return this;
        
        var result = new Shape();
        result.pieceShape = pieceShape;
        
        for (int i = 0; i < 4; i++)
        {
            result.setX(i, -y(i));
            result.setY(i, x(i));
        }
        
        return result;
    }
}
