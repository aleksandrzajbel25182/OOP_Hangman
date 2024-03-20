import stdin.CharSource;
import stdout.Hangman;
import stdout.StdoutGallows;

import java.util.ArrayList;

public class Game {

    // загаданное слово
    final private String word;
    //попытки
    private StringBuilder guess;

    private CharSource source;

    private Hangman hangman;

    private StringBuilder maskWord;

    private ArrayList<Character> playerChar = new ArrayList<>();

    public Game(CharSource source, Hangman hangman, String word) {
        this.source = source;
        this.hangman = hangman;
        this.word = word;
        this.guess = new StringBuilder();
        this.maskWord = new StringBuilder("_".repeat(word.length()));
    }

    public boolean play() {
        while (!this.hangman.hasDied() && !this.isMatch()) {
            var userAnswer = this.source.nextChar();
            var defaultmask = maskWord;
            for (int i = 0; i < word.length(); i++) {
                if (charWordMatch(userAnswer, word, i))
                    maskWord.setCharAt(i, userAnswer);
            }
            if (!defaultmask.equals(maskWord)) {
                hangman.registerAttempts();
                playerChar.add(userAnswer);
            } else {
                //print
            }


        }

        return this.isMatch();
    }

    private boolean isMatch() {
        return this.guess.toString().equals(this.word);
    }

    private boolean charWordMatch(char ch, String str, int index) {
        return str.charAt(index) == ch;
    }

}
