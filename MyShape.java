/**
 * Jaired Jawed
 * Dec 10, 2017
 * MyShape.java
 */

import java.awt.Color;
import java.awt.Graphics;

/**
 * Class contains coordinates and color of shapes
 */

abstract class MyShape {
  private int startX, startY, endX, endY;
  private Color color;

  public MyShape() {
    startX = 0; startY = 0; endX = 0; endY = 0;
    color = Color.BLACK;
  }

  public MyShape(int startX, int startY, int endX, int endY, Color color) {
      this.startX = startX;
      this.startY = startY;
      this.endX = endX;
      this.endY = endY;
      this.color = color;
  }

  /**
   * Mutator methods
   */

  public void setStartX(int startX) {
    this.startX = startX;
  }

  public void setEndX(int endX) {
    this.endX = endX;
  }

  public void setStartY(int startY) {
    this.startY = startY;
  }

  public void setEndY(int endY) {
    this.endY = endY;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  /**
   * Accessor methods
   */

  public int getStartX() {
    return startX;
  }

  public int getEndX() {
    return endX;
  }

  public int getStartY() {
    return startY;
  }

  public int getEndY() {
    return endY;
  }
 
  public Color getColor() {
      return color;
  }

  abstract public void draw( Graphics g );
}
