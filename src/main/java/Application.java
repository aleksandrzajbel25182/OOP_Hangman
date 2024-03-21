import stdin.StdinCharSource;
import stdout.AttemptsHangman;
import stdout.StdoutAnswer;

public class Application {
    public static void main(String[] args) {
        var wordChoice = new WordChoice();
        var charSource = new StdinCharSource();
        var answer = new StdoutAnswer();

        do {
            var word = wordChoice.RandomWord();
            var hangman = new AttemptsHangman();
            var game = new Game(charSource, hangman, word, answer);


            var hasWon = game.play();

            System.out.println(answer.printAnswer());

            if (hasWon) {
                System.out.println("Win");
            } else {

                System.out.println("Game Over");
            }

            System.out.println("Press 'q' to exit");
        } while (charSource.nextChar() != 'q');
    }
}


