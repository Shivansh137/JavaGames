package games.Brickball;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Pad implements KeyListener {
  static final int WIDTH = 120;
  static final int HEIGHT = 20;
  static final int Y = 35;
  int x;
  Color color;

  Pad(int x, Color color) {
    this.x = x;
    this.color = color;
  }

  @Override
  public void keyTyped(KeyEvent e) {
    throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
  }

  @Override
  public void keyPressed(KeyEvent e) {
    this.x -= 3;
  }

  @Override
  public void keyReleased(KeyEvent e) {
    throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
  }
}
