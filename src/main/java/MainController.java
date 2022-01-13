import physics.Plane;
import view.MainWindow;

public class MainController {
    Plane plane;
    FuzzyLogic fuzzy;

    public MainController() {
        plane = new Plane();
        fuzzy = new FuzzyLogic();
    }

    public void tick() {
        int ias = plane.updateIAS();
        int alt = plane.updateAlt();
        int pitch = plane.changePitch(fuzzy.getValue());
    }

    public static void main(String[] args) {
        MainWindow win = new MainWindow();
        win.show();
    }
}
