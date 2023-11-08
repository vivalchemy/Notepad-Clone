import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// could do it with the switch case but it while soon become spaghetti code so had to separate this

// handles the key press of the keyboard and reponds accordingly
public class KeyHandler implements KeyListener {

  GUI gui;

  // gets the editor
  public KeyHandler(GUI gui) {
    this.gui = gui;
  }

  // default class when KeyListener is implemented
  @Override
  public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub
  }

  @Override
  public void keyPressed(KeyEvent e) {
    // TODO Auto-generated method stub
    // short cuts for file menu
    if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_N) {
      gui.file.newFile();
    } else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {
      gui.file.save();
    } else if (e.isShiftDown() && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {
      gui.file.saveAs();
    } else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_O) {
      gui.file.open();
    } else if (e.isAltDown() && e.getKeyCode() == KeyEvent.VK_F) {
      gui.menuFile.doClick();
    } else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_W) {
      System.exit(0);
    }

    // short cuts for edit menu
    else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z) {
      gui.edit.undo();
    } else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Y) {
      gui.edit.redo();
    } else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_X) {
      gui.edit.cut();
    } else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_C) {
      gui.edit.copy();
    } else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
      gui.edit.paste();
    }

    // short cuts for format menu
    else if (e.isAltDown() && e.getKeyCode() == KeyEvent.VK_Z) {
      gui.format.wordWrap();
    }
  }

  // default class when KeyListener is implemented
  @Override
  public void keyReleased(KeyEvent e) {
    // TODO Auto-generated method stub
  }
}
