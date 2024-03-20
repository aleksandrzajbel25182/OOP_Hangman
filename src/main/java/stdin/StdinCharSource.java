package stdin;

import java.util.Scanner;

public class StdinCharSource implements CharSource {

    private Scanner scanner;

    public StdinCharSource(){
        this.scanner = new Scanner(System.in);
    }

    @Override
    public Character nextChar() {
        return  this.scanner.next().charAt(0);
    }
}
