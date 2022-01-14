package physics;

public class Plane {
    private static final double MAX_DELTA = 6;
    private static final double ALT_FACTOR = 0.01;
    private static final double SPEED_FACTOR = -1;
    private static final double PASSIVE_SPEED = 5;
    private static final int SPEED_CENTER = 100;
    private static final int SPEED_MARGIN = 3;
    private static final double MAX_PITCH = 30;

    private final int FPS;

    private double pitch = 0.0;
    private double alt = 200;
    private double ias = 100;

    public Plane(int FPS) {
        this.FPS = FPS;
    }

    public int changePitch(double percent) {
        double percentNow = 100 * pitch / MAX_PITCH;
        double diff = percent - percentNow;
        this.pitch += MAX_DELTA * (diff / 100) / FPS;
        return (int)this.pitch;
    }

    public int updateAlt() {
        this.alt += pitch * ias * ALT_FACTOR / FPS;
        return (int)this.alt;
    }

    public int updateIAS() {
        double mod = 0;
        if (ias < SPEED_CENTER - SPEED_MARGIN) mod = PASSIVE_SPEED;
        else if (ias > SPEED_CENTER + SPEED_MARGIN) mod = -PASSIVE_SPEED;
        this.ias += (pitch * SPEED_FACTOR + mod) / FPS;
        return (int)this.ias;
    }
}
