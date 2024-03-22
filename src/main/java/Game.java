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

    public boolean play() {
        while (this.attemptsHangman.hasDied() && !this.isMatch()) {
            //собираем сообщение
            this.answer.printAnswer(this.gallow.getGallow(this.attemptsHangman.getAttempts()) // висилица
                    + "\nВы использоватли следующие буквы: " + Arrays.toString(this.playerCharList.toArray()) // использованные буквы
                    + "\nНа данный момент слово выглядит так: " + this.maskWord //маска слова
                    + "\nВведите свое предположение:");  //маска слова

            // stdin User
            var userAnswer = this.source.nextChar();
            // check duplicate
            if (!this.playerCharList.contains(userAnswer)) {
                var defaultMask = this.maskWord;
                // check char user for compliance word
                for (int i = 0; i < this.word.length(); i++) {
                    if (charWordMatch(userAnswer, this.word, i))
                        this.maskWord.setCharAt(i, userAnswer);
                }
                if (defaultMask.equals(this.maskWord)) {
                    // register attempts
                    this.attemptsHangman.registerAttempts();
                    this.playerCharList.add(userAnswer);
                    this.answer.printAnswer("Извините, такой буквы в слове нету");

                } else {
                    this.answer.printAnswer("Да такая буква имеется ");
                    this.playerCharList.add(userAnswer);
                }
            } else {
                this.answer.printAnswer("Вы уже вводили такую букву");
            }
        }
        return this.isMatch();
    }

    private boolean isMatch() {
        return this.maskWord.toString().equals(this.word);
    }
    private boolean charWordMatch(char ch, String str, int index) {
        return str.charAt(index) == ch;
    }

}
