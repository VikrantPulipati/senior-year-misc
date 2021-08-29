import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

class MobileAppStuff extends JPanel implements KeyListener {

  public MobileAppStuff () {
    JFrame frame = new JFrame();
    frame.add(this);
    frame.setVisible(true);
    frame.setSize(100, 100);
    setFocusable(true);
    addKeyListener(this);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public void keyReleased (KeyEvent event) {
	  System.out.println("Key Released: " + event.getKeyChar());
  }

  public void keyPressed (KeyEvent event) {
	  System.out.println("Key Pressed: " + event.getKeyChar());
  }

  public void keyTyped (KeyEvent event) {
	  System.out.println("Key Typed: " + event.getKeyChar());
  }

  public static void main(String[] args) {
	  MobileAppStuff m = new MobileAppStuff();
  }

}