package physics;

public class Plane {
    private static final double MAX_DELTA = 0.2;
    private static final double ALT_FACTOR = 1;
    private static final double SPEED_FACTOR = 1;

    private double pitch;
    private double alt;
    private double ias;

    public Plane() {
        this.pitch = 0.0;
    }

    public int changePitch(double percent) {
        this.pitch += MAX_DELTA * (percent / 100);
        return (int)this.pitch;
    }

    public int updateAlt() {
        this.alt += pitch * ias * ALT_FACTOR;
        return (int)this.alt;
    }

    public int updateIAS() {
        this.ias += pitch * SPEED_FACTOR;
        return (int)this.ias;
    }
}
