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
        airspace.setLayout(new GridLayout(3, 3));
        frame.add(airspace);
        airspace.setBackground(Color.BLUE);

        airspace.setSize(400, 400);

        ImageIcon icon = new ImageIcon(resizedImage("src/main/resources/plane.png", 100, 100));
        RotatedIcon rotatedIcon = new RotatedIcon(icon, 30);

        JLabel planeLabel = new JLabel(rotatedIcon);
        planeLabel.setHorizontalAlignment(JLabel.LEFT);
        planeLabel.setVerticalAlignment(JLabel.BOTTOM);

        airspace.add(planeLabel);
//        System.out.println(planeLabel.getVerticalAlignment());
//        planeLabel.setAlignmentX(0.5f);
//        System.out.println(planeLabel.getAlignmentY());

//        rotatedIcon.setDegrees(270);
//        airspace.repaint();
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
