package view;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MainWindow {
    private static final int SKYSIZE = 400;
    private static final int PLANE_SIZE = 200;
    private static final int PLANE_MARGIN = (SKYSIZE - PLANE_SIZE) / 2;
    private static final int INSTRUMENT_H = 50;

    private final JFrame frame;
    private final JPanel airspace;
    private final JPanel instruments;
    private final RotatedIcon planeIcon;
    private final JLabel planeLabel;
    private final JLabel IAS, ALT, GND;
    private final JSlider slider;

    private int ias, alt=398, gnd, setalt;

    public MainWindow() {
        frame = new JFrame();
        frame.setSize(SKYSIZE, 600);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        airspace = new JPanel();
        airspace.setLayout(null);
        airspace.setBackground(Color.BLUE);
        ImageIcon icon = new ImageIcon(resizedImage("src/main/resources/plane.png", PLANE_SIZE, PLANE_SIZE));
        planeIcon = new RotatedIcon(icon, 0);
        planeLabel = new JLabel(planeIcon);
        planeLabel.setBounds(PLANE_MARGIN, PLANE_MARGIN, PLANE_SIZE, PLANE_SIZE);
        airspace.add(planeLabel);

        IAS = createGauge("Speed: -", 0, 0, SKYSIZE / 3, INSTRUMENT_H);
        ALT = createGauge("Alt: - (-)", SKYSIZE / 3, 0, 2 * SKYSIZE / 3, INSTRUMENT_H);
        GND = createGauge("Ground: -", 0, PLANE_MARGIN + PLANE_SIZE + 50, SKYSIZE, INSTRUMENT_H);

        instruments = new JPanel();

        slider = new JSlider(0, 400, 200);
        slider.setMinorTickSpacing(10);
        slider.setMajorTickSpacing(100);
        slider.setPaintTicks(true);
        slider.addChangeListener((ChangeEvent event) -> {
            setalt = slider.getValue();
            regauge();
        });
        instruments.add(slider);

        frame.add(airspace, BorderLayout.CENTER);
        frame.add(instruments, BorderLayout.PAGE_END);
    }

    private Image resizedImage(String path, int x, int y) {
        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage();
        return image.getScaledInstance(x, y, Image.SCALE_SMOOTH);
    }

    private JLabel createGauge(String init, int x, int y, int width, int height) {
        JLabel gauge = new JLabel(init, JLabel.CENTER);
        gauge.setBounds(x, y, width, height);
        gauge.setFont(new Font("Arial", Font.PLAIN,18));
        gauge.setForeground(Color.GREEN);
        airspace.add(gauge);
        return gauge;
    }

    public void regauge() {
        ALT.setText("Altitude: " + alt*100 + " (" + setalt*100 + ")");
        IAS.setText("Speed: " + ias);
        GND.setText("Ground: " + gnd*100);
    }

    public void show() {
        frame.setVisible(true);
    }
}
