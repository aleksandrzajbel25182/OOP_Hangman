package stdout;

public class StdoutAnswer implements Answer {
    private String messageAnswer;

    @Override
    public String printAnswer() {
        return messageAnswer;
    }

    @Override
    public void buildAnswer(String value) {
        this.messageAnswer += value;
    }

}
