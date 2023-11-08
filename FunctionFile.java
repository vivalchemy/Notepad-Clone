import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class FunctionFile {

  GUI gui;// reference to the GUI
  String fileName;// store the file name
  String fileAddress;// store the file address

  // Constructor that takes a reference to the GUI object
  public FunctionFile(GUI gui) {
    this.gui = gui;
  }

  // Clears the text area, sets the window title to "New," and resets file-related
  // variables
  public void newFile() {
    gui.textArea.setText("");// Clear the text area
    gui.window.setTitle("*New File");// Set window title to "New"
    fileName = null;// Reset the file name
    fileAddress = null;// Reset the file address (directory)
  }

  // Opens a file dialog to choose a file to open
  public void open() {
    FileDialog fd = new FileDialog(gui.window, "Open", FileDialog.LOAD);
    fd.setVisible(true);

    if (fd.getFile() != null) {
      fileName = fd.getFile();// Get the selected file name
      fileAddress = fd.getDirectory();// Get the directory path
      gui.window.setTitle(fileName);// Set window title to the opened file name
    }

    try {
      BufferedReader br = new BufferedReader(new FileReader(fileAddress + fileName));// you need the address to read the
                                                                                     // file
      gui.textArea.setText("");// Clear the text area
      String line = null;
      // Read the content of the file and append it to the text area
      while ((line = br.readLine()) != null) {
        gui.textArea.append(line + "\n");
      }

      br.close();// close the file reader
    } catch (Exception e) {
      // System.out.println("FILE NOT OPENED!");
    }
  }

  // Saves the content to the current file or opens the "Save As" dialog if no
  // file is selected
  public void save() {
    if (fileName == null) {
      saveAs();// incase you aren't editing the file save as a new file
      return;
    }
    try {
      FileWriter fw = new FileWriter(fileAddress + fileName);
      fw.write(gui.textArea.getText());// Write the text area content to the file
      gui.window.setTitle(fileName);// Set window title to the saved file name
      fw.close();// Close the file writer
    } catch (Exception e) {
      // System.out.println("Something went wrong!");
    }

  }

  // Opens a "Save As" dialog to choose a file location for saving
  public void saveAs() {
    FileDialog fd = new FileDialog(gui.window, "Save", FileDialog.SAVE);
    fd.setVisible(true);

    if (fd.getFile() != null) {
      fileName = fd.getFile();// Get the selected file name
      fileAddress = fd.getDirectory();// Get the directory address
      gui.window.setTitle(fileName);// Set window title to the saved file name
    }

    try {
      FileWriter fw = new FileWriter(fileAddress + fileName);
      fw.write(gui.textArea.getText()); // Write the text area content to the file
      fw.close();// Close the file writer
    } catch (Exception e) {
      // System.out.println("Something went wrong!");
    }
  }

  // exits the program
  public void exit() {
    System.exit(0);// Terminate the application
  }
}
