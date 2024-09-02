package games.Snake;

import javax.swing.JFrame;

public class SnakeFrame extends JFrame {

  GamePanel gamePanel = new GamePanel();
  public SnakeFrame() {
    this.add(gamePanel);
    this.addKeyListener(gamePanel);
    this.setLocation(450, 80);
    this.setResizable(false);
    this.pack();
    this.setVisible(true);
   }
}
