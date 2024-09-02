package components;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class PlayButton extends JButton {
  PlayButton() {
    this.setText("Play");
    this.setFont(new Font("Arial", Font.BOLD, 16));
    this.setBorder(new EmptyBorder(5, 5, 5, 5));
    this.setBackground(new Color(0xF8981D));
    this.setFocusable(false);
  }
}
