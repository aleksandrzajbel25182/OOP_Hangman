/**
 * @autor Alexander Zaybel
 * @version 1.1
 */

package stdout;

/**
 * The Gallows class contains an array of constants in the form of strings. 
 * The output of the constant is available via the method {@link Gallows#getGallow(int index)}
 */
public class Gallows {

  /**
   * Array of string constants gallows state
   * */
  private static final String[] GALLOWS = new String[]{

      """
     ------
     |    |
     |
     |
     |
     |
     |
    ----------\
""",

      """
     ------
     |    |
     |    O
     |
     |
     |
     |
    ----------\
""",

      """
     ------
     |    |
     |    O
     |    |
     |\s
     |  \s
     |   \s
    ----------\
""",

      """
     ------
     |    |
     |    O
     |   /|
     |  \s
     |  \s
     |  \s
    ----------\
""",

      """
     ------
     |    |
     |    O
     |   /|\\
     |  \s
     |  \s
     |    \s
    ----------\
""",

      """
     ------
     |    |
     |    O
     |   /|\\
     |   /
     |  \s
     |   \s
    ----------\
""",

      """
     ------
     |    |
     |    O
     |   /|\\
     |   / \\
     |  \s
     |  \s
    ----------\
"""
  };

  /**
   * Outputs the value of the array {@link Gallows#GALLOWS}
   *
   * @param index array index
   * @return returns the value of the array
   */
  public String getGallow(int index) {
    return GALLOWS[index];
  }
}

