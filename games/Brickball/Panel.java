package games.Brickball;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel extends JPanel {
  Random rand = new Random();
  Color ballColor = Color.green;
  Color[] colors = {
      new Color(0xee00ff),
      new Color(0xff560d3),
      new Color(0xffee00)
  };
  int x = 0, y = 0;
  int xVelocity = 4, yVelocity = 2;
  Timer timer = new Timer();

  Panel() {
    this.add(new JLabel("Hello World"));
    timer.scheduleAtFixedRate(task, 0, 10);
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setStroke(new BasicStroke(3));
    g2d.setPaint(ballColor);
    g2d.fillOval(x, y, 100, 100);
  }

  TimerTask task = new TimerTask() {
    @Override
    public void run() {
      if (x > getWidth() - 100 || x < 0) {
        xVelocity *= -1;
        ballColor = colors[rand.nextInt(colors.length)];
      }
      if (y > getHeight() - 100 || y < 0) {
        yVelocity *= -1;
        ballColor = colors[rand.nextInt(colors.length)];
      }
      x += xVelocity;
      y += yVelocity;
      repaint();
    }
  };
}
