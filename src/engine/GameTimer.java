package engine;
public class GameTimer {

    private long startTime;

    public GameTimer() {
        this.startTime = 0L;
    }
    public void start() {
        this.startTime = System.nanoTime();
    }
    public int getElapsedTimeInSeconds() {
        if (startTime == 0L) {
            return 0;
        }
        long elapsedNano = System.nanoTime() - this.startTime;
        return (int) (elapsedNano / 1_000_000_000L);
    }
}