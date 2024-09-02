package components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import games.Brickball.BrickBallFrame;
import games.Snake.SnakeFrame;

public class Game implements ActionListener {
  public String name;
  public ImageIcon logo;
  public PlayButton button = new PlayButton();
  public JFrame frame;

  public Game(String name, ImageIcon logo) {
     this.name = name;
    this.logo = logo;
    button.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
   frame = switch (name) {
      case "BrickBall" -> new BrickBallFrame();
      case "Snake" -> new SnakeFrame();
      default -> new JFrame();
   };
  }
}
