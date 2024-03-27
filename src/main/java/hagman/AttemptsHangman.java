/**
 * @autor Alexander Zaybel
 * @version 1.1
 */

package hagman;

import Source.IntputSource;

/**
 * AttemptsHangman class that implements the interface {@link Hagman}
 */
public class AttemptsHangman implements Hagman {

  private int attempts = 0;

  /**
   * Gets attempts.
   *
   * @return the attempts
   */
  public int getAttempts() {
    return this.attempts;
  }

  @Override
  public void registerAttempts() {
    this.attempts++;
  }

  @Override
  public boolean hasDied() {
    return this.attempts < 6;
  }
}
