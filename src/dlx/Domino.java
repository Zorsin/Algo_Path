package dlx;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

/**
 * 21.11.2017
 *
 * @author SWirries
 * <p>
 * This code is
 * documentation enough
 */
public class Domino {
  static final int DOMINO_SIZE = 2;
  /** The X offset of each square of this <code>Pentomino</code>. */
  private final int[] xOffsets = new int[DOMINO_SIZE];

  /** The Y offset of each square of this <code>Pentomino</code>. */
  private final int[] yOffsets = new int[DOMINO_SIZE];
  static final Domino[][] ALL_DOMINOS = new Domino[][] {
      // The D domino: 2 possible orientations.
      {
          new Domino(new String[] {
              "D",
              "D",
          }),
          new Domino(new String[] {
              "DD"
          })
      },
  };

  private Domino(final String[] squares) {
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
