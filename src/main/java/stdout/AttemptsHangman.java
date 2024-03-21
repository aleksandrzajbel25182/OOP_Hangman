package stdout;

public class AttemptsHangman implements Hagman {

    private int attempts = 0;

    public int getAttempts() {
        return this.attempts;
    }

    @Override
    public void registerAttempts() {
        this.attempts++;
    }

    @Override
    public boolean hasDied() {
        return this.attempts < 6;
    }
}
