/**
 * Jaired Jawed
 * Dec 10, 2017
 * DrawFrame.java
 */

import javax.swing.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Color;

public class DrawFrame extends JFrame
{
  private JMenuBar menuBar = new JMenuBar();

  private JMenu fileMenu = new JMenu("File");
  private String[] fileOptions = {"Undo", "Redo", "Clear", "Export To", "Import From"};

  private JMenu shapeMenu = new JMenu("Shapes");
  private String[] shapeOptions = {"Line", "Rectangle", "Oval"};

  private JMenu methodMenu = new JMenu("Method");
  private String[] methodOptions = {"Fill", "Draw"};

  private JPanel colorPanel = new JPanel();
  private String[] colors = {"Black", "Red", "Green", "Blue", "Cyan", "Magenta", "Yellow"};

  DrawPanel panel = new DrawPanel();

  public DrawFrame()
  {
    super("Jaired's Paint Program");

    setJMenuBar(menuBar);

    colorPanel.setLayout(new GridLayout( 1, 6, 10, 10 ));
    add(colorPanel, BorderLayout.NORTH);
    
    add(panel, BorderLayout.CENTER);

    addButtonsToJPanel(colorPanel, colors);

    addMenuItemsToJMenu(fileMenu, fileOptions);
    addMenuItemsToJMenu(shapeMenu, shapeOptions);
    addMenuItemsToJMenu(methodMenu, methodOptions);
    
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(500, 500);
    setVisible(true);
  }

  public void addMenuItemsToJMenu(JMenu menu, String[] arr) {
    MenuHandler handler = new MenuHandler();
    for (int i = 0; i < arr.length; i++) {
      JMenuItem menuItem = new JMenuItem(arr[i]);
      menu.add(menuItem);
      menuItem.addActionListener(handler);
    }
    menuBar.add(menu);
  }

  public void addButtonsToJPanel(JPanel panel, String[] arr) {
    ColorHandler handler = new ColorHandler();
    for (int i = 0; i < arr.length; i++) {
      JButton button = new JButton(arr[i]);
      button.addActionListener(handler);
      colorPanel.add(button);
    }
  }

  private class ColorHandler implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      String actionCommand = event.getActionCommand();
      switch (actionCommand) {
        case "Black":
          panel.setCurrentShapeColor(Color.BLACK);
          break;
        case "Red":
          panel.setCurrentShapeColor(Color.RED);
          break;
        case "Green":
          panel.setCurrentShapeColor(Color.GREEN);
          break;
        case "Blue":
          panel.setCurrentShapeColor(Color.BLUE);
          break;
        case "Cyan":
          panel.setCurrentShapeColor(Color.CYAN);
          break;
        case "Magenta":
          panel.setCurrentShapeColor(Color.MAGENTA);
          break;
        case "Yellow":
          panel.setCurrentShapeColor(Color.YELLOW);
          break;
      }
    }
  }

  private class MenuHandler implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      String actionCommand = event.getActionCommand();
      switch (actionCommand) {
        case "Undo":
          panel.clearLastShape();
        break;
        case "Redo":
          panel.redoLastShape();
        break;
        case "Clear":
          panel.clearDrawing();
        break;
        case "Export To":
          panel.exportImageFromJPanel();
          break;
        case "Import From":
          panel.importImageToJPanel();
          break;
        case "Line":
          panel.setCurrentShapeType("Line");
          break;
        case "Rectangle":
          panel.setCurrentShapeType("Rectangle");
          break;
        case "Oval":
          panel.setCurrentShapeType("Oval");
          break;
        case "Fill":
          panel.setCurrentShapeFilled(true);
          break;
        case "Draw":
          panel.setCurrentShapeFilled(false);
          break;
      }
    }
  }
}
