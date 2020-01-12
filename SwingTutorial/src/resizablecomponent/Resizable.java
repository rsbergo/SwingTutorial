package resizablecomponent;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

/**
 * Represents the component that is being resized and moved on the window.
 */
public class Resizable extends JComponent
{
    public Resizable(Component comp)
    {
        this(comp, new ResizableBorder(8));
    }
    
    public Resizable(Component comp, ResizableBorder border)
    {
        setLayout(new BorderLayout());
        add(comp);
        addMouseListener(resizeListener);
        addMouseMotionListener(resizeListener);
        setBorder(border);
    }
    
    /**
     * The resize() method is called after the component has been resized. The revalidate() method causes the component
     * to be redrawn.
     */
    private void resize()
    {
        if (getParent() != null)
            getParent().revalidate();
    }
    
    MouseInputListener resizeListener = new MouseInputAdapter()
    {
        /**
         * Change the cursor type when hover over the grab points. The cursor type is changed only if the component has
         * focus.
         */
        @Override
        public void mouseMoved(MouseEvent me)
        {
            if (hasFocus())
            {
                var resizableBorder = (ResizableBorder) getBorder();
                setCursor(Cursor.getPredefinedCursor(resizableBorder.getCursor(me)));
            }
        }
        
        @Override
        public void mouseExited(MouseEvent me)
        {
            setCursor(Cursor.getDefaultCursor());
        }
        
        private int cursor;
        private Point startPos = null;
        
        /**
         * Clicking on the resizable component changes the cursor, gets the starting point of dragging, gives focus to
         * the component, and redraws it.
         */
        @Override
        public void mousePressed(MouseEvent me)
        {
            var resizableBorder = (ResizableBorder) getBorder();
            cursor = resizableBorder.getCursor(me);
            startPos = me.getPoint();
            
            requestFocus();
            repaint();
        }
        
        @Override
        public void mouseDragged(MouseEvent me)
        {
            if (startPos != null)
            {
                // Determine the x and y coordinates of the cursor and the width and height of the component. Calculate
                // the distances during the mouse drag example.
                int x = getX();
                int y = getY();
                int w = getWidth();
                int h = getHeight();
                
                int dx = me.getX() - startPos.x;
                int dy = me.getY() - startPos.y;
                
                // For all resizing, ensure that the component is not smaller than 50px, otherwise, it could become so
                // small that it would be hidden. The setBounds() method relocates and resizes the component.
                switch (cursor)
                {
                    case Cursor.N_RESIZE_CURSOR:
                        if (!(h - dy < 50))
                        {
                            setBounds(x, y + dy, w, h - dy);
                            resize();
                        }
                        break;
                    
                    case Cursor.S_RESIZE_CURSOR:
                        if (!(h + dy < 50))
                        {
                            setBounds(x, y, w, h + dy);
                            startPos = me.getPoint();
                            resize();
                        }
                        break;
                    
                    case Cursor.W_RESIZE_CURSOR:
                        if (!(w - dx < 50))
                        {
                            setBounds(x + dx, y, w - dx, h);
                            resize();
                        }
                        break;
                    
                    case Cursor.E_RESIZE_CURSOR:
                        if (!(w + dx < 50))
                        {
                            setBounds(x, y, w + dx, h);
                            startPos = me.getPoint();
                            resize();
                        }
                        break;
                    
                    case Cursor.NW_RESIZE_CURSOR:
                        if (!(w - dx < 50) && !(h - dy < 50))
                        {
                            setBounds(x + dx, y + dy, w - dx, h - dy);
                            resize();
                        }
                        break;
                    
                    case Cursor.NE_RESIZE_CURSOR:
                        if (!(w + dx < 50) && !(h - dy < 50))
                        {
                            setBounds(x, y + dy, w + dx, h - dy);
                            startPos = new Point(me.getX(), startPos.y);
                            resize();
                        }
                        break;
                    
                    case Cursor.SW_RESIZE_CURSOR:
                        if (!(w - dx < 50) && !(h + dy < 50))
                        {
                            setBounds(x + dx, y, w - dx, h + dy);
                            startPos = new Point(startPos.x, me.getY());
                            resize();
                        }
                        break;
                    
                    case Cursor.SE_RESIZE_CURSOR:
                        if (!(w + dx < 50) && !(y + dy < 50))
                        {
                            setBounds(x, y, w + dx, h + dy);
                            startPos = me.getPoint();
                            resize();
                        }
                        break;
                    
                    case Cursor.MOVE_CURSOR:
                        var bounds = getBounds();
                        bounds.translate(dx, dy);
                        setBounds(bounds);
                        resize();
                }
                
                setCursor(Cursor.getPredefinedCursor(cursor));
            }
        }
        
        @Override
        public void mouseReleased(MouseEvent mouseEvent)
        {
            startPos = null;
        }
    };
}
