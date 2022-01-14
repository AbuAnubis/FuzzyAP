package physics;

import java.util.Random;

public class Terrain {
    private static final int MIN_MORA = 0;
    private static final int MAX_MORA = 50;
    private static final int MAX_STEP = 2;

    private double targetMora;
    private final Random engine;

    public Terrain() {
        this.engine = new Random();
        this.targetMora = 10;
    }

    public int randomMora() {
        return engine.nextInt(MAX_MORA - MIN_MORA) + MIN_MORA;
    }

    public double nextStep(double current, int FPS) {
        double step = (double) MAX_STEP / FPS;
        if (Math.abs(current - targetMora) <= step) {
            double ret = targetMora;
            targetMora = randomMora();
            return ret;
        } else if (current < targetMora) {
            return current + step;
        } else {
            return current - step;
        }
    }
}
