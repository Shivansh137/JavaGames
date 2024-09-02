package screens;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import components.Game;
import components.GameCard;

public class HomeScreen extends JPanel {
  Game[] games = {
      new Game("BrickBall", new ImageIcon(getClass().getClassLoader().getResource("images/Java-x-small.png"))),
      new Game("Snake", new ImageIcon(getClass().getClassLoader().getResource("Java-x-small.png")))
  };

  public HomeScreen() {
    this.setBackground(new Color(0x282c32));
    this.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
    this.setOpaque(true);
    this.setVisible(false);
    for (Game game : games) {
      this.add(new GameCard(game.logo, game.name, game.button));
    }
  }
}
