/**
 * Jaired Jawed
 * Dec 10, 2017
 * MyLine.java
 */

import java.awt.Color;
import java.awt.Graphics;

/**
 * This class inherits from MyShape and is responsible for drawing a line.
 */

public class MyLine extends MyShape
{  
    public MyLine() {
        super();
    }
    
    public MyLine(int startX, int startY, int endX, int endY, Color color) {
        super(startX, startY, endX, endY, color);
    }
  
    @Override
    public void draw( Graphics g ) {
        g.setColor(getColor());
        g.drawLine(getStartX(), getStartY(), getEndX(), getEndY());
    }
}
