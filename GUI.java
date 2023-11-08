import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

public class GUI implements ActionListener {
  JFrame window;

  // text area components
  JTextArea textArea;
  JScrollPane scrollPane;
  boolean wordWrapOn = false;

  // top menu bar
  JMenuBar menuBar;
  JMenu menuFile, menuEdit, menuFormat, menuColor;

  // menu items in file section
  JMenuItem iNew, iOpen, iSave, iSaveAs, iExit;

  // menu items in edit section
  JMenuItem iUndo, iRedo, iCut, iCopy, iPaste;

  // menu items in format section
  JMenuItem iWrap, iFontArial, iFontCSMS, iFontTNR, iFontSize8, iFontSize12, iFontSize16, iFontSize20, iFontSize24,
      iFontSize28;
  JMenu menuFont, menuFontSize;

  // menu items in color section
  JMenuItem iColor1, iColor2, iColor3;

  // Instances of functionality classes
  FunctionFile file = new FunctionFile(this);
  FunctionFormat format = new FunctionFormat(this);
  FunctionColor color = new FunctionColor(this);
  FunctionEdit edit = new FunctionEdit(this);

  KeyHandler kHandler = new KeyHandler(this);// KeyHandler to handle keyboard events

  UndoManager um = new UndoManager();// UndoManager to handle undo and redo operations

  public static void main(String[] args) {
    new GUI();
  }

  public GUI() {

    createWindow();// Create the main window
    createTextArea();// Create the text area
    createMenuBar();// Create the menu bar
    createFileMenu();// Create the file menu
    createEditMenu();// Create the edit menu
    createFormatMenu();// Create the format menu
    createColorMenu();// Create the color menu

    format.selectedFont = "Arial";// set the default font to "Arial"
    format.createFont(16);// set the default size to 16
    format.wordWrap();// default word wrap on
    color.changeColor("White");// default bgcolor white
    window.setVisible(true);// make the window visible
  }

  public void createWindow() {

    window = new JFrame("NotePad");// title of the window
    window.setSize(800, 600);// height and width of the window
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// close when X is pressed
  }

  public void createTextArea() {
    textArea = new JTextArea();// create the text area

    textArea.addKeyListener(kHandler);// add the keyboard listner to the text area

    // adds the undo and redo functionality
    textArea.getDocument().addUndoableEditListener(
        new UndoableEditListener() {
          public void undoableEditHappened(UndoableEditEvent e) {
            um.addEdit(e.getEdit());
          }
        });

    // makes the text area scrollable
    scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    // removes the remenants of the default scroll bar border
    scrollPane.setBorder(BorderFactory.createEmptyBorder());
    window.add(scrollPane);// adds the scroll pane to the window
  }

  public void createMenuBar() {
    menuBar = new JMenuBar();
    window.setJMenuBar(menuBar);// adds the menu bar to the window

    menuFile = new JMenu("File");
    menuBar.add(menuFile);// adds the file option
    menuEdit = new JMenu("Edit");
    menuBar.add(menuEdit);// adds edit option
    menuFormat = new JMenu("Format");
    menuBar.add(menuFormat);// adds ...
    menuColor = new JMenu("Color");
    menuBar.add(menuColor);// adds ...

  }

  public void createFileMenu() {

    // add option to create a new file in the file menu
    iNew = new JMenuItem("New(Ctrl + N)");
    iNew.addActionListener(this);
    iNew.setActionCommand("New");
    menuFile.add(iNew);

    // add option to open a new file in the file menu
    iOpen = new JMenuItem("Open(Ctrl + O)");
    iOpen.addActionListener(this);
    iOpen.setActionCommand("Open");
    menuFile.add(iOpen);

    // add option to save a file
    iSave = new JMenuItem("Save(Ctrl + S)");
    iSave.addActionListener(this);
    iSave.setActionCommand("Save");
    menuFile.add(iSave);

    // add option to save a new file
    iSaveAs = new JMenuItem("Save As(Ctrl + Shift + S)");
    iSaveAs.addActionListener(this);
    iSaveAs.setActionCommand("SaveAs");
    menuFile.add(iSaveAs);

    menuFile.addSeparator(); // Add a separator line

    // add option to exit the program i.e same as line: 76
    iExit = new JMenuItem("Exit(Ctrl + X)");
    iExit.addActionListener(this);
    iExit.setActionCommand("Exit");
    menuFile.add(iExit);
  }

  public void createEditMenu() {

    // creates a undo item in edit menu
    iUndo = new JMenuItem("Undo(Ctrl + Z)");
    iUndo.addActionListener(this);
    iUndo.setActionCommand("Undo");
    menuEdit.add(iUndo);

    // creates a redo item in edit menu
    iRedo = new JMenuItem("Redo(Ctrl + Y)");
    iRedo.addActionListener(this);
    iRedo.setActionCommand("Redo");
    menuEdit.add(iRedo);

    menuEdit.addSeparator();// add separator in edit menu

    // add the cut option in the edit menu
    iCut = new JMenuItem("Cut(Ctrl + X)");
    iCut.addActionListener(this);
    iCut.setActionCommand("Cut");
    menuEdit.add(iCut);

    // add the copy option in the edit menu
    iCopy = new JMenuItem("Copy(Ctrl + C)");
    iCopy.addActionListener(this);
    iCopy.setActionCommand("Copy");
    menuEdit.add(iCopy);

    // add the paste option in the edit menu
    iPaste = new JMenuItem("Paste(Ctrl + V)");
    iPaste.addActionListener(this);
    iPaste.setActionCommand("Paste");
    menuEdit.add(iPaste);
  }

  public void createFormatMenu() {

    // add an option to toggle the word wrap
    iWrap = new JMenuItem("Word Wrap : OFF(Alt + Z)");
    iWrap.addActionListener(this);
    iWrap.setActionCommand("Word Wrap");
    menuFormat.add(iWrap);

    // adds the option to set the font of the editor
    menuFont = new JMenu("Font");
    menuFormat.add(menuFont);

    // option to set the font to arial
    iFontArial = new JMenuItem("Arial");
    iFontArial.addActionListener(this);
    iFontArial.setActionCommand("Arial");
    menuFont.add(iFontArial);

    // option to set the font to comic sans
    iFontCSMS = new JMenuItem("Comic Sans MS");
    iFontCSMS.addActionListener(this);
    iFontCSMS.setActionCommand("Comic Sans MS");
    menuFont.add(iFontCSMS);

    // option to set the font ot times new roman
    iFontTNR = new JMenuItem("Times New Roman");
    iFontTNR.addActionListener(this);
    iFontTNR.setActionCommand("Times New Roman");
    menuFont.add(iFontTNR);

    // adds the option to set the font size of the editor
    menuFontSize = new JMenu("Font Size");
    menuFormat.add(menuFontSize);

    // different available font sizes 8, 12, 16, 20 ,24 ,28
    iFontSize8 = new JMenuItem("8");
    iFontSize8.addActionListener(this);
    iFontSize8.setActionCommand("size8");
    menuFontSize.add(iFontSize8);

    iFontSize12 = new JMenuItem("12");
    iFontSize12.addActionListener(this);
    iFontSize12.setActionCommand("size8");
    menuFontSize.add(iFontSize12);

    iFontSize16 = new JMenuItem("16");
    iFontSize16.addActionListener(this);
    iFontSize16.setActionCommand("size16");
    menuFontSize.add(iFontSize16);

    iFontSize20 = new JMenuItem("20");
    iFontSize20.addActionListener(this);
    iFontSize20.setActionCommand("size20");
    menuFontSize.add(iFontSize20);

    iFontSize24 = new JMenuItem("24");
    iFontSize24.addActionListener(this);
    iFontSize24.setActionCommand("size24");
    menuFontSize.add(iFontSize24);

    iFontSize28 = new JMenuItem("28");
    iFontSize28.addActionListener(this);
    iFontSize28.setActionCommand("size28");
    menuFontSize.add(iFontSize28);

  }

  public void createColorMenu() {

    // change the color of the text editor
    iColor1 = new JMenuItem("White");
    iColor1.addActionListener(this);
    iColor1.setActionCommand("White");
    menuColor.add(iColor1);

    iColor2 = new JMenuItem("Black");
    iColor2.addActionListener(this);
    iColor2.setActionCommand("Black");
    menuColor.add(iColor2);

    iColor3 = new JMenuItem("Blue");
    iColor3.addActionListener(this);
    iColor3.setActionCommand("Blue");
    menuColor.add(iColor3);
  }

  // listens to the command send by the menu items
  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    switch (command) {
      case "New":
        file.newFile();
        break;
      case "Open":
        file.open();
        break;
      case "SaveAs":
        file.saveAs();
        break;
      case "Save":
        file.save();
        break;
      case "Exit":
        file.exit();
        break;
      case "Undo":
        edit.undo();
        break;
      case "Redo":
        edit.redo();
        break;
      case "Cut":
        edit.cut();
        break;
      case "Copy":
        edit.copy();
        break;
      case "Paste":
        edit.paste();
        break;

      case "Word Wrap":
        format.wordWrap();
        break;
      case "Arial":
        format.setFont(command);
        break;
      case "Comic Sans MS":
        format.setFont(command);
        break;
      case "Times New Roman":
        format.setFont(command);
        break;
      case "size8":
        format.createFont(8);
        break;
      case "size12":
        format.createFont(12);
        break;
      case "size16":
        format.createFont(16);
        break;
      case "size20":
        format.createFont(20);
        break;
      case "size24":
        format.createFont(24);
        break;
      case "size28":
        format.createFont(28);
        break;
      case "White":
        color.changeColor(command);
        break;
      case "Black":
        color.changeColor(command);
        break;
      case "Blue":
        color.changeColor(command);
        break;

    }
  }
}
