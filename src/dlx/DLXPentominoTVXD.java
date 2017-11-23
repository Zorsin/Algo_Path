package dlx;

import java.util.LinkedList;

/**
 * 21.11.2017
 *
 * @author SWirries
 * <p>
 * This code is
 * documentation enough
 * https://github.com/markoboger/com.fasttrack.sudoku/tree/master/com.jfasttrack.sudoku/src/com/jfasttrack/pentomino
 */
public class DLXPentominoTVXD {
  static int gridWidth;
  static int gridHeigh;
  static int fieldValue;
  public static void main(String[] args) {
    //TODO n einlesen
    int n = 2;
    gridWidth = n;
    gridHeigh = 6;
    fieldValue = gridHeigh*gridWidth;

  }

  public static int cnt = 0;
  public static DLXNode h = new DLXNode();
  private static LinkedList<DLXNode> header;
//  private static LinkedList<int[]> combinations;//Enthält alle Teilmengen
//  public static DLXNode[][] matrix;//Matrix um die Nodes zu verknüpfen

  //////////////CODE VON HERRN HEINZ///////////////////////////////////////////////////////////////////////////////////
  static class DLXNode{
    // represents 1 element or header
    DLXNode C; // reference to column-header
    DLXNode L, R, U, D; // left, right, up, down references
    DLXNode(){
      C=L=R=U=D=this;
    } // supports circular lists
  }

  public static void search (int k){ // finds & counts solutions
    if (h.R == h)
    {
      cnt++;
      return;
    } // if empty: count & done
    DLXNode c = h.R; // choose next column c
    cover(c); // remove c from columns
    for (DLXNode r=c.D; r!=c; r=r.D)
    { // forall rows with 1 in c
      for (DLXNode j=r.R; j!=r; j=j.R) // forall 1-elements in row
        cover(j.C); // remove column
      search(k+1); // recursion
      for (DLXNode j=r.L; j!=r; j=j.L) // forall 1-elements in row
        uncover(j.C); // backtrack: un-remove
    }
    uncover(c); // un-remove c to columns
  }

  public static void cover (DLXNode c){ // remove column c
    c.R.L = c.L ; // remove header
    c.L.R = c.R ; // .. from row list
    for (DLXNode i=c.D; i!=c; i=i.D) // forall rows with 1
      for (DLXNode j=i.R; i!=j; j=j.R)
      { // forall elem in row
        j.D.U = j.U ; // remove row element
        j.U.D = j.D ; // .. from column list
      }
  }

  public static void uncover (DLXNode c){//undo remove col c
    for (DLXNode i=c.U; i!=c; i=i.U){
      // forall rows with 1
      for (DLXNode j=i.L; i!=j; j=j.L){ // forall elem in row
        j.D.U = j ; // un-remove row elem
        j.U.D = j ; // .. to column list
      }}
    c.R.L = c ; // un-remove header
    c.L.R = c ; // .. to row list
  }


  public static void createDomino(int n, int fieldWidth){
//    int count = 1;
//    combinations = new LinkedList<>();
//    for(int i = 1;i<=fieldWidth-3;i++){
//      if(i%n!=0 && count<6){
//        //TODO
//      }
//    }
    for(int pieceIndex = 0; pieceIndex < Domino.ALL_DOMINOS.length;pieceIndex++){
      for(int orientation = 0;orientation<Domino.ALL_DOMINOS[pieceIndex].length;orientation++){
        Domino domino = Domino.ALL_DOMINOS[pieceIndex][orientation];
        rowLoop:
          for(int row = 0;row < gridHeigh;row++) {//gridhight
            for (int i = 0; i < Domino.DOMINO_SIZE; i++) {
              if (row + domino.getYOffset(i) >= gridHeigh) {
                break rowLoop;
              }
            }

            columnLoop:
            for (int column = 0; column < gridWidth; column++) {
              for (int i = 0; i < Domino.DOMINO_SIZE; i++) {
                if (column + domino.getXOffset(i) >= gridWidth) {
                  break columnLoop;
                }
              }
              createRow(pieceIndex,orientation,row,column);
            }
          }
      }
    }

  }
  static void createRow(int pieceIndex, int orientation,int row, int column){
    int pieceidentifier = pieceIndex * Domino.ALL_DOMINOS.length+orientation;
    int location = row * gridWidth +column;
    int nodeValue = pieceidentifier * fieldValue + location;

    Domino domino = Domino.ALL_DOMINOS[pieceIndex][orientation];
    DLXNode rowHeader = new DLXNode();
    //AppData store nodeValue
    header.add(rowHeader);
  }
}
