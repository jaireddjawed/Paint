/**
 * Jaired Jawed
 * Dec 10, 2017
 * MyRectangle.java
 */

import java.awt.Color;
import java.awt.Graphics;

/**
 * This class inherits from MyBoundedShape and is responsible for drawing a rectangle
 */

public class MyRectangle extends MyBoundedShape
{ 
    public MyRectangle() {
        super();
    }

    public MyRectangle(int startX, int startY, int endX, int endY, Color color, boolean fill) {
        super(startX, startY, endX, endY, color, fill);
    }
    
    @Override
    public void draw( Graphics g ) {
        g.setColor(getColor());
        if (getFill()) {
            g.fillRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        }
        else {
            g.drawRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        }
    }
}
