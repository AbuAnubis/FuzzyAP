package view;

import javax.swing.*;
import java.awt.*;

public class MainWindow {
    private final JFrame frame;
    private final JPanel airspace;

    public MainWindow() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        airspace = new JPanel();
        frame.add(airspace);
        airspace.setBackground(Color.BLUE);

        airspace.setSize(400, 400);

        ImageIcon icon = new ImageIcon(resizedImage("src/resources/plane.png", 100, 100));
        RotatedIcon rotatedIcon = new RotatedIcon(icon, 30);
        airspace.add(new JLabel(rotatedIcon));

        rotatedIcon.setDegrees(270);
        airspace.repaint();
    }

    private Image resizedImage(String path, int x, int y) {
        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage();
        return image.getScaledInstance(x, y, Image.SCALE_SMOOTH);
    }

    public void show() {
        frame.setSize(400, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
