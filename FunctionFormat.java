import java.awt.Font;// used to edit the fonts in the textarea

public class FunctionFormat {

  GUI gui;
  Font arial, comicSansMS, timesNewRoman;
  String selectedFont;

  // Constructor that takes a reference to the GUI object
  public FunctionFormat(GUI gui) {
    this.gui = gui;
  }

  // Toggles word wrap on and off in the text area
  public void wordWrap() {
    if (gui.wordWrapOn == false) {
      gui.wordWrapOn = true;
      gui.textArea.setLineWrap(true);
      gui.textArea.setWrapStyleWord(true);
      gui.iWrap.setText("Word Wrap : ON(Alt + Z)");
    } else {
      gui.wordWrapOn = false;
      gui.textArea.setLineWrap(false);
      gui.textArea.setWrapStyleWord(false);
      gui.iWrap.setText("Word Wrap : OFF(Alt + Z)");
    }
  }

  // Creates different fonts with the given font size
  public void createFont(int fontSize) {
    arial = new Font("Arial", Font.PLAIN, fontSize);
    comicSansMS = new Font("Comic Sans MS", Font.PLAIN, fontSize);
    timesNewRoman = new Font("Times New Roman", Font.PLAIN, fontSize);

    setFont(selectedFont);
  }

  // Sets the selected font for the text area
  public void setFont(String font) {
    selectedFont = font;

    switch (selectedFont) {
      case "Arial":
        gui.textArea.setFont(arial);
        break;
      case "Comic Sans MS":
        gui.textArea.setFont(comicSansMS);
        break;
      case "Times New Roman":
        gui.textArea.setFont(timesNewRoman);
        break;
    }
  }
}
