import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import screens.SplashScreen;
import screens.HomeScreen;

public class MainFrame extends JFrame implements Runnable {
  JPanel panel;
  SplashScreen splashScreen = new SplashScreen();
  HomeScreen homeScreen = new HomeScreen();
  Thread removeSplash;

  MainFrame() {
    this.setTitle("JavaGames");
    this.setIconImage(new ImageIcon("images/Java.png").getImage());
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setLocation(350, 150);
    this.setResizable(false);
    this.add(splashScreen);
    this.setVisible(true);
    removeSplash = new Thread(this, "Remove SplashScreen");
    this.setSize(800, 650);
    removeSplash.start();
  }

  @Override
  public void run() {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      System.out.println(e.getMessage());
    } finally {
      splashScreen.setVisible(false);
      this.remove(splashScreen);
      this.add(homeScreen);
      homeScreen.setVisible(true);
    }
  }
}
