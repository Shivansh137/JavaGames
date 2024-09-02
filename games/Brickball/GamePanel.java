package games.Brickball;

//imports-----------------------
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

//------------------------------------
public class GamePanel extends JPanel implements KeyListener {
  // instance variables ---------------------------------
  static final int WIDTH = 1000;
  static final int HEIGHT = 700;
  int radius = 10;
  int brickGap = 10;
  int ballSpeed = 2;
  Timer loop = new Timer();
  boolean isGameOver = false;
  Color ballColor = Color.white;
  int ballVelocityX = ballSpeed;
  Brick[] bricks = new Brick[30];
  int ballVelocityY = -1 * ballSpeed;
  Pad pad = new Pad(500 - Pad.WIDTH / 2, Color.red);
  int ballX = WIDTH / 2 - radius, ballY = HEIGHT - Pad.Y - 2 * radius - 10;

  // -----------------------------------------------------
  void makeBricks() {
    for (int i = 1; i <= brickGap; i++) {
      Brick b = new Brick(brickGap * i + Brick.WIDTH * (i - 1), brickGap, Color.green);
      bricks[i - 1] = b;
    }
    for (int i = 11; i <= 20; i++) {
      Brick b = new Brick(brickGap * (i == 20 ? 10 : i % brickGap) + Brick.WIDTH * (i == 20 ? 9 : (i % brickGap) - 1),
          20 + Brick.HEIGHT, Color.green);
      bricks[i - 1] = b;
    }
    for (int i = 21; i <= 30; i++) {
      Brick b = new Brick(brickGap * (i % 20) + Brick.WIDTH * (i % 20 - 1), 30 + 2 * Brick.HEIGHT, Color.green);
      bricks[i - 1] = b;
    }
  }

  // constructor-----------------------------------
  public GamePanel() {
    this.setBackground(new Color(0x282c32));
    this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    makeBricks();
    loop.scheduleAtFixedRate(tt, 0, 5);
  }

  // -----------------------------------------------------------
  // paint ----------------------------
  @Override
  public void paint(Graphics g) {
    super.paint(g);
    Graphics2D g2d = (Graphics2D) g;
    for (Brick brick : bricks) {
      g2d.setPaint(brick.color);
      g2d.fillRect(brick.x, brick.y, Brick.WIDTH, Brick.HEIGHT);
    }
    g2d.setPaint(pad.color);
    g2d.fillRect(pad.x, getHeight() - Pad.Y, Pad.WIDTH, Pad.HEIGHT);
    g2d.setPaint(ballColor);
    g2d.fillOval(ballX, ballY, 2 * radius, 2 * radius);
    if (isGameOver) {
      // game over Screen-----------------------------
      g2d.setPaint(Color.black);
      g2d.fillRect(0, 0, WIDTH, HEIGHT);
      g2d.setFont(new Font("monospace", Font.BOLD, 94));
      g2d.setColor(Color.white);
      g2d.drawString("Brick Ball\n", WIDTH / 2 - 200, HEIGHT / 2 - 80);
      g2d.setFont(new Font("monospace", Font.BOLD, 24));
      g2d.setColor(Color.green);
      g2d.drawString("Press space to start \n", WIDTH / 2 - 110, HEIGHT / 2 + 50);
    }
  }
  // listener ---------------------------
  @Override
  public void keyTyped(KeyEvent e) {}

  @Override
  public void keyPressed(KeyEvent e) {
    if (!isGameOver) {
      if (pad.x > 0 && e.getKeyCode() == 37) {
        pad.x -= 20;
      }
      if (pad.x + Pad.WIDTH < this.getWidth() && e.getKeyCode() == 39) {
        pad.x += 20;
      }
    }
    if (e.getKeyCode() == 32 && isGameOver) {
      isGameOver = false;
    }
    repaint();
  }

  @Override
  public void keyReleased(KeyEvent e) {}

  // Timer Task--------------------------
  TimerTask tt = new TimerTask() {
    @Override
    public void run() {
      if (!isGameOver) {
        // move ball -------------------
        if (ballX > getWidth() - 2 * radius || ballX < 0) {
          ballVelocityX *= -1;
        }
        if (ballY < 0) {
          ballVelocityY *= -1;
        }
        ballX += ballVelocityX;
        ballY += ballVelocityY;
      }
      // check pad-ball collision-------------------
      if (ballX <= pad.x + Pad.WIDTH &&
              ballX + (2 * radius) >= pad.x &&
              ballY + 2 * radius == (getHeight() - Pad.Y) &&
              ballVelocityY > 0) {
        ballVelocityY *= -1;
      }
      // check game over----------------------------
      if (ballY > getHeight()) {
        isGameOver = true;
        ballY = HEIGHT - Pad.Y - 2 * radius - 10;
        ballVelocityY *= -1;
        makeBricks();
      }
      // check brick-ball collision-----------------
      for (Brick brick : bricks) {
        if (ballX <= brick.x + Brick.WIDTH &&
                ballX + (2 * radius) >= brick.x &&
                ballY + 2 * radius >= brick.y &&
                ballY <= brick.y + Brick.HEIGHT) {
          ballVelocityY *= -1;
          brick.y = -100;
          ballSpeed++;
        }
      }
      repaint();
    }
  };
}
