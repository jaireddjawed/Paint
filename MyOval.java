/**
 * Jaired Jawed
 * Dec 10, 2017
 * MyOval.java
 */

import java.awt.Color;
import java.awt.Graphics;

/**
 * This class inherits from MyBoundedShape and is responsible for drawing a oval.
 */

public class MyOval extends MyBoundedShape 
{ 
    public MyOval() {
        super();
    }
    
    public MyOval(int startX, int startY, int endX, int endY, Color color, boolean fill) {
        super(startX, startY, endX, endY, color, fill);
    }

    @Override
    public void draw( Graphics g ) {
        g.setColor(getColor());
        if (getFill()) {
            g.fillOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        }
        else {
            g.drawOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        }
    }   
}
