import java.awt.Color;

public class FunctionColor {

  GUI gui;

  // Constructor that takes a reference to the GUI object
  public FunctionColor(GUI gui) {
    this.gui = gui;
  }

  // changes the color of the font and the text area background based on the selection
  public void changeColor(String color) {
    switch (color) {
      case "White":
        gui.textArea.setBackground(Color.white);
        gui.textArea.setForeground(Color.black);
        break;
      case "Black":
        gui.textArea.setBackground(Color.black);
        gui.textArea.setForeground(Color.white);
        break;
      case "Blue":
        gui.textArea.setBackground(Color.blue);
        gui.textArea.setForeground(Color.yellow);
        break;
    }
  }
}
