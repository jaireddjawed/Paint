/**
 * Jaired Jawed
 * Dec 10, 2017
 * MyBoundedShape.java
 */

import java.awt.Color;
import java.awt.Graphics;

/**
 * This is an abstract class with an abstract draw method. It inherits from MyShape
 * and contains methods needed for drawing ovals and rectangles. It also contains an instance variable called fill.
 */
abstract class MyBoundedShape extends MyShape
{
    private boolean fill; // boolean variable that determines whether the shape is filled or not
    
    public MyBoundedShape() {
        super();
        fill = false;
    }
    
    public MyBoundedShape(int startX, int startY, int endX, int endY, Color color, boolean fill) {
        super(startX, startY, endX, endY, color);
        this.fill = fill;
    }
    
    /**
     * Mutator methods
     */

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    /**
     * Accessor Methods
     */
  
    public int getUpperLeftX() {
        return Math.min(getStartX(), getEndX());
    }

    public int getUpperLeftY() {
        return Math.min(getStartY(), getEndY());
    }
    
    public int getWidth() {
        return Math.abs(getStartX() - getEndX());
    }
  
    public int getHeight() {
        return Math.abs(getStartY() - getEndY());
    }
    
    public boolean getFill() {
        return fill;
    }
    
    abstract public void draw( Graphics g );
}
