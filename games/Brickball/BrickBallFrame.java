package games.Brickball;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class BrickBallFrame extends JFrame {
  GamePanel gamePanel = new GamePanel();

  public BrickBallFrame() {
    this.addKeyListener(gamePanel);
    this.setTitle("BrickBall - JGames");
    this.setLocation(300, 100);
    this.add(gamePanel);
    this.setResizable(false);
    this.setAlwaysOnTop(true);
    this.setVisible(true);
    this.pack();
  }
}
