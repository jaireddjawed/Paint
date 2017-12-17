/**
 * Jaired Jawed
 * Dec 10, 2017
 * DrawPanel.java
 */

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.nio.file.FileAlreadyExistsException;

public class DrawPanel extends JPanel 
{
  private LinkedList<MyShape> myShapes; // dynamic stack of shapes
  private LinkedList<MyShape> clearedShapes; // dynamic stack of cleared shapes from undo

  // current shape variables
  private String currentShapeType; // allowed values are "Line", "Rectangle", and "Oval"
  private MyShape currentShapeObject; // stores the current Shape being used
  private Color currentShapeColor; // current shape color
  private boolean currentShapeFilled; // determines whether shape is filled or not

  private JLabel statusLabel;

  public DrawPanel() 
  {
    myShapes = new LinkedList<MyShape>();
    clearedShapes = new LinkedList<MyShape>();

    // Initialize default Shape variables
    currentShapeType = "Line";
    currentShapeObject = null;
    currentShapeColor = Color.BLACK;
    currentShapeFilled = false;

    statusLabel = new JLabel("");

    setLayout(new BorderLayout());
    setBackground(Color.WHITE);
    add(statusLabel, BorderLayout.SOUTH);

    MouseHandler handler = new MouseHandler();                             
    addMouseListener(handler);
    addMouseMotionListener(handler);
  }

  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);

    int width = getWidth(), height = getHeight();

    ArrayList<MyShape> shapeArr = myShapes.getArray();
    for (int counter = shapeArr.size() - 1; counter >= 0; counter --) {
      shapeArr.get(counter).draw(g);
    }

    if (currentShapeObject != null) {
      currentShapeObject.draw(g);
    }
  }

  // clears the entire drawing
  public void clearDrawing() {
    myShapes.makeEmpty();
    clearedShapes.makeEmpty();
    repaint();
  }

  // called when undo menu item is clicked
  public void clearLastShape() {
    if (!myShapes.isEmpty()) {
        clearedShapes.addFront(myShapes.removeFront());
        repaint();
    }
  }

  // called when redo menu item is clicked
  public void redoLastShape() {
    if (!clearedShapes.isEmpty()) {
        myShapes.addFront(clearedShapes.removeFront());
        repaint();
    }
  }

  public void importImageToJPanel() {
    try {
      String fileName = JOptionPane.showInputDialog("Enter a file name:");
      if (fileName.equals(null) || fileName.trim().equals("") ) {
        throw new Exception("Please enter a file name!");
      }

      BufferedImage image = ImageIO.read(new File("./" + fileName + ".png"));
      add(new JLabel(new ImageIcon(image)));
    }
    catch (FileNotFoundException e) {
      JOptionPane.showMessageDialog(null, "The file was not found!");
    }
    catch (IOException e) {
      JOptionPane.showMessageDialog(null, "There was an error reading the file!");
    }
    catch (Exception e) {
      JOptionPane.showMessageDialog(null, e);
    }
  }

  public void exportImageFromJPanel() {
    try {
      String fileName = JOptionPane.showInputDialog("Enter a file name:");

      if (fileName.equals(null) || fileName.trim().equals("") ) {
        throw new Exception("Please enter a file name!");
      }

      File file = new File("./" + fileName + ".png");
      
      if (file.exists()) {
        throw new FileAlreadyExistsException("File already exists.");
      }

      BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
      paint(img.createGraphics());
      
      ImageIO.write(img, "PNG", file);
    }
    catch (FileAlreadyExistsException e) {
      JOptionPane.showMessageDialog(null, "This file already exists");
    }
    catch (Exception e) {
      JOptionPane.showMessageDialog(null, e);
    }
  }

  /**
   * Mutator Methods
   */

  public void setCurrentShapeType(String type) {
    currentShapeType = type;
  }

  public void setCurrentShapeColor(Color color) {
      currentShapeColor = color;
  }

  public void setCurrentShapeFilled(boolean filled) {
      currentShapeFilled = filled;
  }

  private class MouseHandler extends MouseAdapter
  {
    public void mousePressed(MouseEvent event)
    {
      switch (currentShapeType) {
          case "Line":
              currentShapeObject = new MyLine( event.getX(), event.getY(), 
                                             event.getX(), event.getY(), currentShapeColor);
              break;
          case "Rectangle":
              currentShapeObject = new MyRectangle( event.getX(), event.getY(), 
                                                  event.getX(), event.getY(), currentShapeColor, currentShapeFilled);
              break;
          case "Oval":
              currentShapeObject = new MyOval( event.getX(), event.getY(), 
                                             event.getX(), event.getY(), currentShapeColor, currentShapeFilled);
              break;
      }
    }

    public void mouseReleased(MouseEvent event)
    {
        currentShapeObject.setEndX(event.getX());
        currentShapeObject.setEndY(event.getY());
        
        myShapes.addFront(currentShapeObject);
        
        currentShapeObject = null;
        clearedShapes.makeEmpty();
        repaint();
    }

    public void mouseMoved(MouseEvent event)
    {
        statusLabel.setText(String.format("Mouse Coordinates X: %d Y: %d", event.getX(), event.getY()));
    }

    public void mouseDragged(MouseEvent event)
    {
        currentShapeObject.setEndX(event.getX());
        currentShapeObject.setEndY(event.getY());
        
        statusLabel.setText(String.format("Mouse Coordinates X: %d Y: %d", event.getX(), event.getY()));
        
        repaint();
    }
  }
}
