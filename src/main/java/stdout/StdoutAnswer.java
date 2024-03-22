package stdout;

public class StdoutAnswer implements Answer {
    @Override
    public void printAnswer(String message) {
        System.out.println(message);
    }
}
