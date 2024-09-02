package screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SplashScreen extends JPanel {
  JLabel label = new JLabel();

  public SplashScreen() {
    this.setBackground(new Color(0x000));
    this.setOpaque(true);
    this.setLayout(new BorderLayout());
    label.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Java640x427.png")));
    label.setFont(new Font("Verdana", Font.PLAIN, 32));
    label.setText("JGames");
    label.setIconTextGap(5);
    label.setHorizontalAlignment(JLabel.CENTER);
    label.setVerticalTextPosition(JLabel.BOTTOM);
    label.setHorizontalTextPosition(JLabel.CENTER);
    label.setForeground(Color.white);
    this.add(label, BorderLayout.CENTER);
  }
}
