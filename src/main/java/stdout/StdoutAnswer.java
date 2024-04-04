/**
 * @autor Alexander Zaybel
 * @version 1.1
 */

package stdout;

/**
 * Class standard response output
 */
public class StdoutAnswer implements Answer {

  @Override
  public void print(String message) {
    System.out.println(message);
  }
}
