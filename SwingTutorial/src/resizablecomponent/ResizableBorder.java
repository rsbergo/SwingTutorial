package resizablecomponent;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 * ResizableBorder is responsible for drawing the border of the component and determining the type of cursor to use.
 */
public class ResizableBorder implements Border
{
    // Data Fields
    private int dist = 8;
    
    // These are the locations where the rectangles are drawn. These locations are also grabbing points where it is
    // possible to grab the component and resize it.
    int locations[] = { SwingConstants.NORTH, SwingConstants.SOUTH, SwingConstants.WEST, SwingConstants.EAST,
                        SwingConstants.NORTH_WEST, SwingConstants.NORTH_EAST, SwingConstants.SOUTH_WEST,
                        SwingConstants.SOUTH_EAST };
    
    int cursors[] = { Cursor.N_RESIZE_CURSOR, Cursor.S_RESIZE_CURSOR, Cursor.W_RESIZE_CURSOR, Cursor.E_RESIZE_CURSOR,
                      Cursor.NW_RESIZE_CURSOR, Cursor.NE_RESIZE_CURSOR, Cursor.SW_RESIZE_CURSOR, 
                      Cursor.SE_RESIZE_CURSOR };
    
    // Methods
    /**
     * Constructor.
     */
    public ResizableBorder(int dist)
    {
        this.dist = dist;
    }
    
    @Override
    public Insets getBorderInsets(Component component)
    {
        return new Insets(dist, dist, dist, dist);
    }
    
    @Override
    public boolean isBorderOpaque()
    {
        return false;
    }
    
    /**
     * Draw the border of the resizable component.
     */
    @Override
    public void paintBorder(Component component, Graphics g, int x, int y, int w, int h)
    {
        // Draws the outer border of the component.
        g.setColor(Color.black);
        g.drawRect(x + dist / 2, y + dist / 2, w - dist, h - dist);
        
        // The eight rectangles are drawn only in case that the resizable component currently has focus.
        if (component.hasFocus())
        {
            for (int i = 0; i < locations.length; i++)
            {
                var rect = getRectangle(x, y, w, h, locations[i]);
                
                g.setColor(Color.white);
                g.fillRect(rect.x, rect.y, rect.width - 1, rect.height - 1);
                g.setColor(Color.black);
                g.drawRect(rect.x, rect.y, rect.width - 1, rect.height - 1);
            }
        }
    }
    
    /**
     * Returns the coordinates of a rectangle.
     */
    private Rectangle getRectangle(int x, int y, int w, int h, int location)
    {
        switch (location)
        {
            case SwingConstants.NORTH:
                return new Rectangle(x + w / 2 - dist / 2, y, dist, dist);
            
            case SwingConstants.SOUTH:
                return new Rectangle(x + w / 2 - dist / 2, y + h - dist, dist, dist);
            
            case SwingConstants.WEST:
                return new Rectangle(x, y + h / 2 - dist / 2, dist, dist);
            
            case SwingConstants.EAST:
                return new Rectangle(x + w - dist, y + h / 2 - dist / 2, dist, dist);
            
            case SwingConstants.NORTH_WEST:
                return new Rectangle(x, y, dist, dist);
            
            case SwingConstants.NORTH_EAST:
                return new Rectangle(x + w - dist, y, dist, dist);
            
            case SwingConstants.SOUTH_WEST:
                return new Rectangle(x, y + h - dist, dist, dist);
            
            case SwingConstants.SOUTH_EAST:
                return new Rectangle(x + w - dist, y + h - dist, dist, dist);
        }
        return null;
    }
    
    /**
     * Gets the cursor type for the grab point in question.
     */
    public int getCursor(MouseEvent me)
    {
        var c = me.getComponent();
        int w = c.getWidth();
        int h = c.getHeight();
        
        for (int i = 0; i < locations.length; i++)
        {
            var rect = getRectangle(0, 0, w, h, locations[i]);
            
            if (rect.contains(me.getPoint()))
                return cursors[i];
        }
        
        return Cursor.MOVE_CURSOR;
    }
}
