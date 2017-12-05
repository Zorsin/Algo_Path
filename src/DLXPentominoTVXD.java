import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 27.11.2017
 *
 * @author Sören Wirries, Micha Heiß
 * <p>
 * This code is
 * documentation enough
 *
 *
 *
 * Werte für a(n):
 *
 * n = 0, a(n)  = 0
 * n = 1, a(n)  = 1
 * n = 2, a(n)  = 13
 * n = 3, a(n)  = 229
 * n = 4, a(n)  = 1309
 * n = 5, a(n)  = 16035
 * n = 6, a(n)  = 180166
 * n = 7, a(n)  = 1704648
 * n = 8, a(n)  = 18538436
 * n = 9, a(n)  = 196622355
 * n = 10, a(n) = 2029339183
 *
 *
 */
public class DLXPentominoTVXD {

    private static DLXNode h = new DLXNode();
    private static long count = 0;
    private static int n;


    public static void main(String[] args) {

        // TODO delete
        for(int i = 0; i<15; i++){
            System.out.println(count6Xn(i));
        }

        BufferedReader br = null;
        while (true) {
            br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Bitte geben sie ein gewünschtes n ein: ");
            String input = "";
            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("n=" + input);
            try {
                n = Integer.parseInt(input);
            } catch (Exception e) {
//            e.printStackTrace();
                System.out.println("dieses n ist keine Zahl.");
            }
            System.out.println(count6Xn(n));
        }
    }


    public static long count6Xn(int n) {
        if(n==0) return 0;
        DLXMatrix m = new DLXMatrix(6 * n);
        setAllPossibleTPentominoPositions(m, n);
        setALlPossibleVPentominoPositions(m, n);
        setAllXPentominoPositions(m, n);
        setAllPossibleDominoPositions(m, n);

        h = m.root;
        count = 0;
        search(0);

        return count;
    }

    public static int[] addDelta(int x, int y, int[][] delta) {
        int[] indices = new int[delta.length];
        for(int i = 0; i < indices.length; i++) {
            indices[i] = getMatrixPositionByCoordinates(x + delta[i][0], y + delta[i][1]);
        }
        return indices;
    }

    public static void mirror(int[][] polynomino, int mirrorIndex) {
        for(int i = 0; i < polynomino.length; i++) {
            polynomino[i][mirrorIndex] = -polynomino[i][mirrorIndex];
        }
    }

    public static int getMatrixPositionByCoordinates(int x, int y) {
        return y * 6 + x;
    }


    public static void setAllPossibleDominoPositions(DLXMatrix m, int n) {
        for(int y = 0; y < n-1; y++) {
            for(int x = 0; x < 6-1; x++) {
                m.addRow(getMatrixPositionByCoordinates(x, y), getMatrixPositionByCoordinates(x + 1, y));
                m.addRow(getMatrixPositionByCoordinates(x, y), getMatrixPositionByCoordinates(x, y + 1));
            }
            m.addRow(getMatrixPositionByCoordinates(6-1, y), getMatrixPositionByCoordinates(6-1, y + 1));
        }
        for(int x = 0; x < 6-1; x++) {
            m.addRow(getMatrixPositionByCoordinates(x, n-1), getMatrixPositionByCoordinates(x + 1, n-1));
        }
    }

    public static void setAllPossibleTPentominoPositions(DLXMatrix m, int n) {
        int[][] d = new int[5][2];
        d[0] = new int[]{0, 0};
        d[1] = new int[]{0, -1};
        d[2] = new int[]{0, +1};
        d[3] = new int[]{-1, -1};
        d[4] = new int[]{+1, -1};
        int[][] dSide = new int[5][2];
        dSide[0] = new int[]{0, 0};
        dSide[1] = new int[]{-1, 0};
        dSide[2] = new int[]{+1, 0};
        dSide[3] = new int[]{-1, -1};
        dSide[4] = new int[]{-1, +1};
        for(int y = 1; y < n-1; y++) {
            for(int x = 1; x < 6-1; x++) {
                m.addRow(addDelta(x, y, d));
                mirror(d, 1);
                m.addRow(addDelta(x, y, d));
                mirror(d, 1);
                m.addRow(addDelta(x, y, dSide));
                mirror(dSide, 0);
                m.addRow(addDelta(x, y, dSide));
                mirror(dSide, 0);
            }
        }
    }

    public static void setALlPossibleVPentominoPositions(DLXMatrix m, int n) {
        int[][] d = new int[5][2];
        d[0] = new int[]{-1, -1};
        d[1] = new int[]{-1, 0};
        d[2] = new int[]{0, -1};
        d[3] = new int[]{+1, -1};
        d[4] = new int[]{-1, +1};
        for(int y = 1; y < n-1; y++) {
            for(int x = 1; x < 6-1; x++) {
                m.addRow(addDelta(x, y, d));
                mirror(d, 0);
                m.addRow(addDelta(x, y, d));
                mirror(d, 1);
                m.addRow(addDelta(x, y, d));
                mirror(d, 0);
                m.addRow(addDelta(x, y, d));
                mirror(d, 1);
            }
        }
    }

    public static void setAllXPentominoPositions(DLXMatrix m, int n) {
        for(int x = 1; x < 6-1; x++) {
            for(int y = 1; y < n-1; y++) {
                int[] indices = new int[5];
                indices[0] = getMatrixPositionByCoordinates(x, y);
                indices[1] = getMatrixPositionByCoordinates(x - 1, y);
                indices[2] = getMatrixPositionByCoordinates(x + 1, y);
                indices[3] = getMatrixPositionByCoordinates(x, y - 1);
                indices[4] = getMatrixPositionByCoordinates(x, y + 1);
                m.addRow(indices);
            }
        }
    }


    /**
     * search tries to find and count all complete coverings of the DLX matrix.
     * Is a recursive, depth-first, backtracking algorithm that finds
     * all solutions to the exact cover problem encoded in the DLX matrix.
     * each time all columns are covered, static long count is increased
     *
     * @param k: number of level
     */
    public static void search(int k) { // finds & counts solutions
        if (h.right == h) {
            count++;
            return;
        }     // if empty: count & done
        DLXNode c = h.right;                   // choose next column c
        cover(c);                          // remove c from columns
        for (DLXNode r = c.down; r != c; r = r.down) {  // forall rows with 1 in c
            for (DLXNode j = r.right; j != r; j = j.right) // forall 1-elements in row
                cover(j.CHeader);                    // remove column
            search(k + 1);                    // recursion
            for (DLXNode j = r.left; j != r; j = j.left) // forall 1-elements in row
                uncover(j.CHeader);                  // backtrack: un-remove
        }
        uncover(c);                        // un-remove c to columns
    }

    /**
     * cover "covers" a column c of the DLX matrix
     * column c will no longer be found in the column list
     * rows i with 1 element in column c will no longer be found
     * in other column lists than c
     * so column c and rows i are invisible after execution of cover
     *
     * @param c: header element of column that has to be covered
     */
    public static void cover(DLXNode c) { // remove column c
        c.right.left = c.left;                         // remove header
        c.left.right = c.right;                         // .. from row list
        for (DLXNode i = c.down; i != c; i = i.down)      // forall rows with 1
            for (DLXNode j = i.right; i != j; j = j.right) {   // forall elem in row
                j.down.up = j.up;                     // remove row element
                j.up.down = j.down;                     // .. from column list
            }
    }

    /**
     * uncover "uncovers" a column c of the DLX matrix
     * all operations of cover are undone
     * so column c and rows i are visible again after execution of uncover
     *
     * @param c: header element of column that has to be uncovered
     */
    public static void uncover(DLXNode c) {//undo remove col c
        for (DLXNode i = c.up; i != c; i = i.up)      // forall rows with 1
            for (DLXNode j = i.left; i != j; j = j.left) {   // forall elem in row
                j.down.up = j;                       // un-remove row elem
                j.up.down = j;                       // .. to column list
            }
        c.right.left = c;                           // un-remove header
        c.left.right = c;                           // .. to row list
    }

}

/**
 * Class DLXNode
 * represents a matrix element of the cover matrix with value 1
 * links go to up down left right neighbors, and column header
 * can also be used as column header or root of column headers
 * matrix is sparsely coded
 * try to do all operations very efficiently
 * see:
 * http://en.wikipedia.org/wiki/Dancing_Links
 * http://arxiv.org/abs/cs/0011047
 */
class DLXNode {       // represents 1 element or header
    DLXNode CHeader;           // reference to column-header
    DLXNode left, right, up, down;  // left, right, up, down references

    DLXNode() {
        CHeader = left = right = up = down = this;
    } // supports circular lists
}

class DLXMatrix {

    DLXNode root;
    DLXNode[] CHeader;

    int size;

    public DLXMatrix(int size) {
        this.size = size;
        root = new DLXNode();
        CHeader = new DLXNode[size];
        for(int i = 0; i < size; i++) {
            CHeader[i] = new DLXNode();
            if(i == 0) {
                CHeader[i].left = root;
                root.right = CHeader[i];
            } else {
                CHeader[i].left = CHeader[i-1];
                CHeader[i-1].right = CHeader[i];
            }
        }
        root.left = CHeader[size - 1];
        CHeader[size - 1].right = root;

    }

    public void addRow(int... oneIndices) {
        DLXNode[] row = new DLXNode[oneIndices.length];
        for(int i = 0; i < oneIndices.length; i++) {
            row[i] = new DLXNode();
            if(i > 0) {
                row[i].left = row[i-1];
                row[i-1].right = row[i];
            }
        }
        row[row.length - 1].right = row[0];
        row[0].left = row[row.length - 1];

        for(int i = 0; i < row.length; i++) {
            row[i].CHeader = CHeader[oneIndices[i]];
            row[i].down = CHeader[oneIndices[i]];
            row[i].up = CHeader[oneIndices[i]].up;
            CHeader[oneIndices[i]].up.down = row[i];
            CHeader[oneIndices[i]].up = row[i];
        }
    }

}

