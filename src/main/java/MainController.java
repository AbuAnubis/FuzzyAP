import physics.Plane;
import physics.Terrain;
import view.MainWindow;

public class MainController extends Thread {
    private static final int FPS = 60;

    private final Plane plane;
    private final FuzzyLogic fuzzy;
    private final MainWindow window;
    private final Terrain terrain;
    private double mora = 10;

    public MainController() {
        plane = new Plane(FPS);
        fuzzy = new FuzzyLogic(true);
        window = new MainWindow();
        terrain = new Terrain();
        window.show();
    }

    public void tick() {
        mora = terrain.nextStep(mora, FPS);
        int ias = plane.updateIAS();
        int alt = plane.updateAlt();
        double gnd = alt - mora;
        int altDelta = window.getSetAlt() - alt;
        fuzzy.setValues(ias, altDelta, gnd);
        int pitch = plane.changePitch(fuzzy.getValue());
        window.rotate(pitch);
        window.regauge(ias, alt, mora);
    }

    public void run() {
        while(true) {
            tick();
            try {
                //noinspection BusyWait
                Thread.sleep(1000 / FPS);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        MainController mc = new MainController();
        mc.start();
        try {
            mc.join();
        } catch (InterruptedException ignored) {

        }
        System.out.println("Ended");
    }
}
