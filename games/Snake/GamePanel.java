package games.Snake;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener {

  Random random = new Random();
  // instance variables----------------------------
  static final int WIDTH = 600;
  static final int HEIGHT = 600;
  static final int NO_OF_ROWS = 20;
  static final int CELL_SIZE = WIDTH / 20;
  boolean isGameOver = false;
  // COLORS----------------
  Color[] colors = {
    new Color(0x383CC1),
    new Color(0x6EC72D),
    new Color(0xA77B06),
    new Color(0x5A20CB),
    new Color(0x3DBE29),
    new Color(0xFF6666),
    new Color(0x383CC1),
    new Color(0xAF9D5A),
    new Color(0xE03B8B),
    new Color(0x6A1B4D),
  };

   Color BGCOLOR = new Color(0x242B2E);
   Color SNAKE_COLOR = Color.blue;

  Graphics2D g2d;
  int noOfPieces = 0;
  String dir = "UP";

  // apple X and Y-----------------------------------
  int appleX = random.nextInt(NO_OF_ROWS) * CELL_SIZE;
  int appleY = random.nextInt(NO_OF_ROWS) * CELL_SIZE;

   // ---------------------TIMER ---------------------
  Timer timer = new Timer();
  TimerTask timerTask = new TimerTask() {
    @Override
    public void run() {
      if (!isGameOver) {
        checkAppleCollision();
        moveSnake();
     }
    }
  };
  // -------------------SNAKE ----------------------------------

  ImageIcon[] snakeHeads = {
      new ImageIcon(getClass().getClassLoader().getResource("images/snake_head_up.png")),
      new ImageIcon(getClass().getClassLoader().getResource("images/snake_head_down.png")),
      new ImageIcon(getClass().getClassLoader().getResource("images/snake_head_left.png")),
      new ImageIcon(getClass().getClassLoader().getResource("images/snake_head_right.png"))
  };

  ImageIcon[] snakeTails = {
          new ImageIcon(getClass().getClassLoader().getResource("images/snake_tail_up.png")),
          new ImageIcon(getClass().getClassLoader().getResource("images/snake_tail_down.png")),
                  new ImageIcon(getClass().getClassLoader().getResource("images/snake_tail_left.png")),
                  new ImageIcon(getClass().getClassLoader().getResource("images/snake_tail_right.png"))
  };

  ImageIcon currentHead = snakeHeads[0];
  ImageIcon currentTail = snakeTails[0];

 static class SnakePiece {
    int x, y;
    SnakePiece(int x, int y) {
      this.x = x * CELL_SIZE;
      this.y = y * CELL_SIZE;
    }
  }

  SnakePiece[] snakePieces = new SnakePiece[400];

  // ADD SNAKE PIECES ------------------------
  void addSnakePiece(SnakePiece sp) {
    snakePieces[noOfPieces] = sp;
    noOfPieces++;
  }

  ImageIcon snakeBody = new ImageIcon(getClass().getClassLoader().getResource("images/snake_body.png"));

  // DRAW SNAKE --------------------
  void drawSnake() {
    g2d.setPaint(SNAKE_COLOR);
    for (int i = 0; i < noOfPieces; i++) {
      if (i == 0) {
        g2d.drawImage(currentHead.getImage(), snakePieces[i].x, snakePieces[i].y, null);
      }
      else if (i == noOfPieces-1) {
        g2d.drawImage(currentTail.getImage(), snakePieces[i].x, snakePieces[i].y, null);
      }
      else {
        g2d.drawImage(snakeBody.getImage(), snakePieces[i].x, snakePieces[i].y, null);
      }
    }
  }

  // MOVE SNAKE-----------------------------
  void moveSnake() {
    for (int i=noOfPieces-1; i>=0; i--) {
      if (i != 0) {
        if (snakePieces[i].x == snakePieces[0].x && snakePieces[i].y == snakePieces[0].y) {

        }
        snakePieces[i].x = snakePieces[i - 1].x;
        snakePieces[i].y = snakePieces[i - 1].y;
      }
      else {
        switch (dir) {
          case "UP":
            snakePieces[i].y -= CELL_SIZE;
            break;
          case "LEFT":
            snakePieces[i].x -= CELL_SIZE;
            break;
          case "RIGHT":
            snakePieces[i].x += CELL_SIZE;
            break;
          case "DOWN":
            snakePieces[i].y += CELL_SIZE;
            break;
        }
      }
      if (snakePieces[noOfPieces - 1].y > snakePieces[noOfPieces - 2].y) {
        currentTail = snakeTails[0];
      }
      if (snakePieces[noOfPieces - 1].y < snakePieces[noOfPieces - 2].y) {
        currentTail = snakeTails[1];
      }
      if (snakePieces[noOfPieces - 1].x < snakePieces[noOfPieces - 2].x) {
        currentTail = snakeTails[3];
      }
      if (snakePieces[noOfPieces - 1].x > snakePieces[noOfPieces - 2].x) {
        currentTail = snakeTails[2];
      }
    }
    repaint();
  }
  // ---------------------CONSTRUCTOR-------------------
  GamePanel() {
    this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    this.setBackground(BGCOLOR);
    addSnakePiece(new SnakePiece(10, 10));
    addSnakePiece(new SnakePiece(10, 9));
    addSnakePiece(new SnakePiece(10, 8));
    timer.scheduleAtFixedRate(timerTask, 0, 100);
  }


  void drawGrid() {
    g2d.setPaint(new Color(0x282C32));
    g2d.setStroke(new BasicStroke(2));
    for (int i = 1; i <= NO_OF_ROWS; i++) {
      g2d.drawLine(i * CELL_SIZE, 0, i * CELL_SIZE, HEIGHT);
      g2d.drawLine(0, i * CELL_SIZE, WIDTH, i * CELL_SIZE);
    }
  }

// -----------------------APPLE---------------------------
  void drawApple() {
    g2d.drawImage(new ImageIcon(getClass().getClassLoader().getResource("images/apple_image.png")).getImage(), appleX,appleY, null);
  }

  void moveApple() {
    appleX = random.nextInt(NO_OF_ROWS) * CELL_SIZE;
    appleY = random.nextInt(NO_OF_ROWS) * CELL_SIZE;
    repaint();
  }

  void restart(){
    dir = "UP";
    appleX = random.nextInt(NO_OF_ROWS) * CELL_SIZE;
    appleY = random.nextInt(NO_OF_ROWS) * CELL_SIZE;
    noOfPieces = 0;
    currentHead = snakeHeads[0];
    currentTail = snakeTails[0];
    snakePieces = new SnakePiece[400];
    addSnakePiece(new SnakePiece(10, 10));
    addSnakePiece(new SnakePiece(10, 9));
    addSnakePiece(new SnakePiece(10, 8));
    isGameOver = false;
  }


  // ------------------- COLLISIONS -------------------
  void checkAppleCollision() {
    if (appleX == snakePieces[0].x && appleY == snakePieces[0].y) {
      moveApple();
      snakePieces[noOfPieces] = new SnakePiece(snakePieces[0].x, snakePieces[0].y);
      noOfPieces++;
      SNAKE_COLOR = colors[random.nextInt(colors.length)];
      repaint();
    }
    // snake collision
    if (snakePieces[0].x < 0 || snakePieces[0].x > WIDTH || snakePieces[0].y < 0 || snakePieces[0].y > HEIGHT) {
      isGameOver = true;
      restart();
    }
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    g2d = (Graphics2D) g;
   drawGrid();
    drawApple();
    drawSnake();
  }

  // -------------------- KEYLISTENER ----------------
  @Override
  public void keyTyped(KeyEvent e) {
  }
  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case 37:
        if (dir != "RIGHT") {
          dir = "LEFT";
          currentHead = snakeHeads[2];
        }
        break;
      case 38:
        if (dir != "DOWN") {
          dir = "UP";
          currentHead = snakeHeads[0];
        }
        break;
      case 39:
        if (dir != "LEFT") {
          dir = "RIGHT";
          currentHead = snakeHeads[3];
        }
        break;
      case 40:
        if (dir != "UP") {
          dir = "DOWN";
          currentHead = snakeHeads[1];
        }
        break;
    }
  }
  @Override
  public void keyReleased(KeyEvent e) {
  }

}
