import stdin.CharSource;
import stdout.Answer;
import stdout.Gallows;
import stdout.AttemptsHangman;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {

    // загаданное слово
    final private String word;
    private CharSource source;
    private AttemptsHangman attemptsHangman;
    private StringBuilder maskWord;
    private Answer answer;
    private Gallows gallow;
    private ArrayList<Character> playerCharList = new ArrayList<>();

    public Game(CharSource source, AttemptsHangman attemptsHangman, String word, Answer answer) {
        this.source = source;
        this.attemptsHangman = attemptsHangman;
        this.word = word;
        this.answer = answer;
        this.maskWord = new StringBuilder("_".repeat(word.length()));
        this.gallow = new Gallows();
    }

    public boolean getStateGame() {
        return stateGame;
    }

    private boolean stateGame = false;

    public boolean play() {
        stateGame = true;
        while (this.attemptsHangman.hasDied() && !this.isMatch()) {
            //собираем сообщение
            this.answer.buildAnswer(this.gallow.getGallow(this.attemptsHangman.getAttempts()) // висилица
                    + "\nВы использоватли следующие буквы: " + Arrays.toString(playerCharList.toArray()) // использованные буквы
                    + "=\nНа данный момент слово выглядит так: " + this.maskWord //маска слова
                    + "\nВведите свое предположение:");  //маска слова

            // stdin User
            var userAnswer = this.source.nextChar();
            // check duplicate
            if (!playerCharList.contains(userAnswer)) {
                var defaultMask = maskWord;
                // check char user for compliance word
                for (int i = 0; i < word.length(); i++) {
                    if (charWordMatch(userAnswer, word, i))
                        maskWord.setCharAt(i, userAnswer);
                }
                if (!defaultMask.equals(maskWord)) {
                    // register attempts
                    attemptsHangman.registerAttempts();
                    playerCharList.add(userAnswer);
                    this.answer.buildAnswer("Извините, такой буквы в слове нету");
                } else {
                    answer.buildAnswer("Да такая буква имеется ");
                }
            } else {
                this.answer.buildAnswer("Вы уже вводили такую букву");
            }
            this.answer.printAnswer();
        }
        stateGame = this.isMatch();
        return stateGame;
    }

    private boolean isMatch() {
        return this.maskWord.toString().equals(this.word);
    }

    private boolean charWordMatch(char ch, String str, int index) {
        return str.charAt(index) == ch;
    }


}
