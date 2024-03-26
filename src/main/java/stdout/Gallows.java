package stdout;

public class Gallows {

  private static final String[] gallows = new String[]{

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

  public String getGallow(int index) {
    return gallows[index];
  }
}

