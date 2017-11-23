package dlx;

/**
 * 21.11.2017
 *
 * @author SWirries
 * <p>
 * This code is
 * documentation enough
 * https://github.com/markoboger/com.fasttrack.sudoku/tree/master/com.jfasttrack.sudoku/src/com/jfasttrack/dlx
 */
public class Pentomino {
  static final int PENTOMINO_SIZE = 5;
  /** The X offset of each square of this <code>Pentomino</code>. */
  private final int[] xOffsets = new int[PENTOMINO_SIZE];

  /** The Y offset of each square of this <code>Pentomino</code>. */
  private final int[] yOffsets = new int[PENTOMINO_SIZE];
  static final Pentomino[][] ALL_PENTOMINOES = new Pentomino[][] {
      // The T pentomino: 4 possible orientations.
      {
          new Pentomino(new String[] {
              "TTT",
              " T",
              " T",
          }),
          new Pentomino(new String[] {
              "  T",
              "TTT",
              "  T",
          }),
          new Pentomino(new String[] {
              " T ",
              " T ",
              "TTT",
          }),
          new Pentomino(new String[] {
              "T  ",
              "TTT",
              "T  ",
          }),
      },
      // The V pentomino: 4 possible orientations.
      {
          new Pentomino(new String[] {
              "V  ",
              "V  ",
              "VVV",
          }),
          new Pentomino(new String[] {
              "VVV",
              "V  ",
              "V  ",
          }),
          new Pentomino(new String[] {
              "VVV",
              "  V",
              "  V",
          }),
          new Pentomino(new String[] {
              "  V",
              "  V",
              "VVV",
          }),
      },
      // The X pentomino: 1 possible orientation.
      {
          new Pentomino(new String[] {
              " X ",
              "XXX",
              " X ",
          }),
      }

  };

  private Pentomino(final String[] squares) {
    int index = 0;
    for (int r = 0; r < squares.length; r++) {
      for (int c = 0; c < squares[r].length(); c++) {
        if (squares[r].charAt(c) != ' ') {
          xOffsets[index] = c;
          yOffsets[index] = r;
          index++;
        }
      }
    }
  }
  public int getXOffset(final int index) {
    return xOffsets[index];
  }
  public int getYOffset(final int index) {
    return yOffsets[index];
  }
}
