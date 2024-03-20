package stdout;

public class StdoutGallows extends Hangman {
    @Override
    public void printConsoleGallows() {
        System.out.println(Gallows.getGallows(0));
    }
}

