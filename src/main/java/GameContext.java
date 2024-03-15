import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GameContext {

    private static final String[] hangman = new String[]{

            "     ------\n" +
                    "     |    |\n" +
                    "     |\n" +
                    "     |\n" +
                    "     |\n" +
                    "     |\n" +
                    "     |\n" +
                    "    ----------",

            "     ------\n" +
                    "     |    |\n" +
                    "     |    O\n" +
                    "     |\n" +
                    "     |\n" +
                    "     |\n" +
                    "     |\n" +
                    "    ----------",

            "     ------\n" +
                    "     |    |\n" +
                    "     |    O\n" +
                    "     |    |\n" +
                    "     | \n" +
                    "     |   \n" +
                    "     |    \n" +
                    "    ----------",

            "     ------\n" +
                    "     |    |\n" +
                    "     |    O\n" +
                    "     |   /|\n" +
                    "     |   \n" +
                    "     |   \n" +
                    "     |   \n" +
                    "    ----------",

            "     ------\n" +
                    "     |    |\n" +
                    "     |    O\n" +
                    "     |   /|\\\n" +
                    "     |   \n" +
                    "     |   \n" +
                    "     |     \n" +
                    "    ----------",

            "     ------\n" +
                    "     |    |\n" +
                    "     |    O\n" +
                    "     |   /|\\\n" +
                    "     |   /\n" +
                    "     |   \n" +
                    "     |    \n" +
                    "    ----------",

            "     ------\n" +
                    "     |    |\n" +
                    "     |    O\n" +
                    "     |   /|\\\n" +
                    "     |   / \\\n" +
                    "     |   \n" +
                    "     |   \n" +
                    "    ----------"
    };

    private final int MAX_WRONG = hangman.length - 1;
    private ArrayList<Character> charUserList = new ArrayList<>();

    private int wrong = 0;

    private State state;

    private Scanner scanner;

    private Character userAnswer;

    private WordChoice wordChoice;

    private StringBuilder randomWord;

    private StringBuilder maskWord;

    public GameContext() {
        this.state = new StartState();
    }

    public void start() {
        while (!(this.state instanceof EndState)) {
            try {
                this.state.execute();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public abstract class State {

        public void execute() {
            this.executeImpl();
            if (!equals(this.getStateInfo())) {
                System.out.println(this.getStateInfo());
            }

        }

        public abstract void executeImpl();

        public abstract String getStateInfo();
    }

    private class StartState extends State {

        @Override
        public void executeImpl() {
            scanner = new Scanner((System.in));
            wordChoice = new WordChoice();
            randomWord = new StringBuilder(wordChoice.RandomWord());
            String s = "_".repeat(randomWord.length());
            maskWord = new StringBuilder(s);
            state = new PrintHangman();
        }

        @Override
        public String getStateInfo() {
            return "Добро пожаловать в Виселицу";
        }
    }

    private class EndState extends State {

        @Override
        public void executeImpl() {
            scanner.close();
        }

        @Override
        public String getStateInfo() {
            return "";
        }

    }

    private class UserInputState extends State {

        @Override
        public void executeImpl() {
            userAnswer = scanner.next().charAt(0);
            state = new CheckDuplicate();
        }

        @Override
        public String getStateInfo() {
            return null;
        }
    }

    private class CheckCharUser extends State {

        @Override
        public void executeImpl() {

            int numberMatches = 0;
            for (int i = 0; i < randomWord.length(); i++) {

                if (randomWord.charAt(i) == userAnswer) {
                    maskWord.setCharAt(i, userAnswer);
                    numberMatches += 1;
                }
            }
            if (numberMatches == 0) {
                wrong += 1;
                state = new LoseAttemptState();
            } else {
                state = new WinAttemptState();
            }
        }

        @Override
        public String getStateInfo() {
            return null;
        }

    }

    private class LoseAttemptState extends State {

        @Override
        public void executeImpl() {
            state = new PrintHangman();
        }

        @Override
        public String getStateInfo() {
            return "Извините такой буквы нету в слове!";
        }
    }

    private class WinAttemptState extends State {

        @Override
        public void executeImpl() {
            state = new PrintHangman();
        }

        @Override
        public String getStateInfo() {
            return "Да, буква " + userAnswer + " имется в слове";
        }
    }

    private class WinGameState extends State {
        @Override
        public void executeImpl() {
            state = new EndState();
        }

        @Override
        public String getStateInfo() {
            return "Поздравляем с победой";
        }
    }

    private class LoseGameState extends State {
        @Override
        public void executeImpl() {
            state = new EndState();
        }

        @Override
        public String getStateInfo() {
            return "Тебя повесили. Слово было: " + randomWord.toString();
        }
    }

    private class CheckDuplicate extends State {

        @Override
        public void executeImpl() {
            if (charUserList.contains(userAnswer)) {
                state = new UserInputState();
            }
            state = new CheckCharUser();
        }

        @Override
        public String getStateInfo() {
            return "\nВы уже вводили такую букву";
        }
    }

    private class MaskWord extends State {

        @Override
        public void executeImpl() {
            state = new UserInputState();
        }

        @Override
        public String getStateInfo() {

            return "\nНа данный момент слово выглядит так: " + maskWord + "\nВведите свое предположение: ";
        }
    }

    private class PrintDuplicate extends State {

        @Override
        public void executeImpl() {
            state = new MaskWord();
        }

        @Override
        public String getStateInfo() {
            return "Вы использовали следующие буквы: " + Arrays.toString(charUserList.toArray());
        }
    }

    private class PrintHangman extends State {

        @Override
        public void executeImpl() {

            if (wrong == MAX_WRONG) {
                state = new LoseGameState();
            } else if (maskWord == randomWord) {
                state = new WinGameState();
            } else {
                state = new PrintDuplicate();
            }

        }

        @Override
        public String getStateInfo() {
            return hangman[wrong];
        }
    }
}
