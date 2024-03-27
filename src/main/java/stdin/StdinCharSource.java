/**
 * @autor Alexander Zaybel
 * @version 1.1
 */

package stdin;

import java.util.Scanner;

/**
 * The type Stdin char source.
 */
public class StdinCharSource implements CharSource {

  private Scanner scanner;

  /**
   * Instantiates a new Stdin char source.
   */
  public StdinCharSource() {
    this.scanner = new Scanner(System.in);
  }

  @Override
  public Character nextChar() {
    return this.scanner.next().charAt(0);
  }
}
