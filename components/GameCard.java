package components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameCard extends JPanel {
  JLabel label = new JLabel();

  public GameCard(ImageIcon imageIcon, String name, JButton button) {
    this.setLayout(new BorderLayout());
    label.setFont(new Font("Arial", Font.BOLD, 16));
    label.setForeground(Color.white);
    label.setBackground(Color.black);
    label.setOpaque(true);
    label.setText(name);
    label.setIcon(imageIcon);
    label.setVerticalTextPosition(JLabel.BOTTOM);
    label.setHorizontalTextPosition(JLabel.CENTER);
    label.setHorizontalAlignment(JLabel.CENTER);
    label.setIconTextGap(15);
    label.setPreferredSize(new Dimension(200, 220));
    this.add(label, BorderLayout.NORTH);
    this.add(button, BorderLayout.SOUTH);
  }
}
