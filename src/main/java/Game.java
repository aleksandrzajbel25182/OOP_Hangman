import java.util.ArrayList;
import java.util.Arrays;

import stdin.CharSource;
import stdout.Answer;
import hagman.AttemptsHangman;
import stdout.Gallows;

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
            String message = this.gallow.getGallow(this.attemptsHangman.getAttempts()) // висилица
                + "\nВы использоватли следующие буквы: " + Arrays.toString(this.playerCharList.toArray()) // использованные буквы
                + "\nНа данный момент слово выглядит так: " + this.maskWord //маска слова
                + "\nВведите свое предположение:";  //маска слова

            this.answer.printAnswer(message);

            var userAnswer = nextChar();
            if (!checkDuplicate(userAnswer)) {
                continue;
            }
            if (!checkCharWord(userAnswer)) {
                this.attemptsHangman.registerAttempts();
                this.playerCharList.add(userAnswer);
                this.answer.printAnswer("Извините, такой буквы в слове нету");
            }else {
                this.answer.printAnswer("Да такая буква имеется ");
                this.playerCharList.add(userAnswer);
            }
        }

        return this.isMatch();
    }

    private boolean isMatch() {
        return this.maskWord.toString().equals(this.word);
    }

    private boolean checkCharWord(char ch){
        var index = 0;
        boolean flag = false;
        //Нахождение символа в слове и измениние маски
        while (index > -1) {
            index = this.word.indexOf(ch, index);

            if (index != -1) {
                this.maskWord.setCharAt(index, ch);
                index++;
                flag = true;
            }
        }
        return  flag;
    }

    private boolean checkDuplicate(char ch){

        if (!this.playerCharList.contains(ch)){
            return true;
        }else {
            this.answer.printAnswer("Вы уже вводили такую букву");
            return false;
        }
    }

    private Character nextChar(){
        return this.source.nextChar();
    }
}
