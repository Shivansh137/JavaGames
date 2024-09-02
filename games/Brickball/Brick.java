package games.Brickball;

import java.awt.Color;
import java.util.Random;

public class Brick {
  static final int WIDTH = 89;
  static final int HEIGHT = 18;
  int x, y;
  Color color;
  Random r = new Random();
  Color[] colors = {
          Color.green,
          Color.pink,
          Color.orange,
          Color.red,
          Color.yellow,
          Color.CYAN,
          Color.magenta
  };

  int i = r.nextInt(colors.length);

  Brick(int x, int y, Color color) {
    this.x = x;
    this.y = y;
    this.color = colors[i];
  }
}
