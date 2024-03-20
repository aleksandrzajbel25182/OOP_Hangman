package stdout;

public abstract class Hangman {

    private int attempts = 0;

    public void registerAttempts() {
        this.attempts++;
    }

    public boolean hasDied() {
        return this.attempts < 6;
    }

    public abstract void printConsoleGallows();
}
