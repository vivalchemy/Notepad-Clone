public class FunctionEdit {
  GUI gui;

  // Constructor that takes a reference to the GUI object
  public FunctionEdit(GUI gui) {
    this.gui = gui;
  }

  // Perform the undo operation using the UndoManager
  public void undo() {
    gui.um.undo();
  }

  // Perform the redo operation using the UndoManager
  public void redo() {
    gui.um.redo();
  }

  // cuts the selected text in text area
  public void cut() {
    gui.textArea.cut();
  }

  // copy the selected text in text area
  public void copy() {
    gui.textArea.copy();
  }

  // paste the text in clipboard in text area or replaces the selected text with
  // the text in clipboard
  public void paste() {
    gui.textArea.paste();
  }
}
