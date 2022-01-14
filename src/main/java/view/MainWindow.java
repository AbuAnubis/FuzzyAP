package view;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;

public class MainWindow {
    private static final int SKYSIZE = 400;
    private static final int PLANE_SIZE = 300;
    private static final int PLANE_MARGIN = (SKYSIZE - PLANE_SIZE) / 2;
    private static final int INSTRUMENT_H = 50;
    private static final String ICON_PATH = "src/main/resources/plane.png";

    private final JFrame frame;
    private final JPanel airspace;
    private final RotatedIcon planeIcon;
    private final JLabel IAS, ALT, GND;
    private final JSlider slider;

    private int ias, alt, setalt = 200;
    private double gnd;

    public MainWindow() {
        frame = new JFrame("Fuzzy A/P");
        frame.setSize(SKYSIZE, 600);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        airspace = new JPanel();
        airspace.setLayout(null);
        airspace.setBackground(new Color(0, 122, 212));
        ImageIcon icon = new ImageIcon(resizedImage());
        planeIcon = new RotatedIcon(icon, 0);
        JLabel planeLabel = new JLabel(planeIcon);
        planeLabel.setBounds(PLANE_MARGIN, PLANE_MARGIN, PLANE_SIZE, PLANE_SIZE);
        airspace.add(planeLabel);

        IAS = createGauge("Speed: -", 0, 0, SKYSIZE / 3);
        ALT = createGauge("Altitude: - (-)", SKYSIZE / 3, 0, 2 * SKYSIZE / 3);
        GND = createGauge("Ground: -", 0, PLANE_MARGIN + PLANE_SIZE + 50, SKYSIZE);

        JPanel instruments = new JPanel();

        slider = new JSlider(0, 400, 200);
        slider.setMinorTickSpacing(10);
        slider.setMajorTickSpacing(100);
        slider.setPaintTicks(true);
        slider.addChangeListener((ChangeEvent event) -> setalt = slider.getValue());
        instruments.add(slider);

        frame.add(airspace, BorderLayout.CENTER);
        frame.add(instruments, BorderLayout.PAGE_END);
    }

    private Image resizedImage() {
        ImageIcon icon = new ImageIcon(MainWindow.ICON_PATH);
        Image image = icon.getImage();
        return image.getScaledInstance(MainWindow.PLANE_SIZE, MainWindow.PLANE_SIZE, Image.SCALE_SMOOTH);
    }

    private JLabel createGauge(String init, int x, int y, int width) {
        JLabel gauge = new JLabel(init, JLabel.CENTER);
        gauge.setBounds(x, y, width, MainWindow.INSTRUMENT_H);
        gauge.setFont(new Font("Arial", Font.PLAIN,18));
        gauge.setForeground(Color.GREEN);
        airspace.add(gauge);
        return gauge;
    }

    private Color colorIAS(double ias) {
        if (85 <= ias && ias <= 115) return Color.GREEN;
        else if (50 <= ias && ias <= 150) return Color.ORANGE;
        else return Color.RED;
    }

    private Color colorALT(double altDelta) {
        if (-10 <= altDelta && altDelta <= 10) return Color.GREEN;
        else if (-70 <= altDelta && altDelta <= 70) return Color.ORANGE;
        else return Color.RED;
    }

    private Color colorGND(double gnd) {
        if (50 <= Math.abs(gnd)) return Color.GREEN;
        else if (20 <= Math.abs(gnd)) return Color.ORANGE;
        else return Color.RED;
    }

    public void rotate(int degrees) {
        planeIcon.setDegrees(-degrees);
        airspace.repaint();
    }

    public void regauge() {
        ALT.setText("Altitude: " + alt*100 + " (" + setalt*100 + ")");
        ALT.setForeground(colorALT(alt - setalt));
        IAS.setText("Speed: " + ias);
        IAS.setForeground(colorIAS(ias));
        GND.setText("Ground: " + (int)(gnd*100) + " (" + (int)((gnd - alt)*100) + ")");
        GND.setForeground(colorGND(gnd - alt));
    }

    public void regauge(int ias, int alt, double gnd) {
        this.ias = ias;
        this.alt = alt;
        this.gnd = gnd;
        regauge();
    }

    public int getSetAlt() {
        return setalt;
    }

    public void show() {
        frame.setVisible(true);
    }
}
