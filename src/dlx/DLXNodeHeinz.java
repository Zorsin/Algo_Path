package dlx;

/**
 * 21.11.2017
 *
 * @author SWirries
 * <p>
 * This code is
 * documentation enough
 */
public class DLXNodeHeinz {

  DLXNodeHeinz C; // reference to column-header
//  static DLXNodeHeinz h;
  DLXNodeHeinz L, R, U, D; // left, right, up, down references
  DLXNodeHeinz(){ C=L=R=U=D=this; }


  public static void search (int k){ // finds & counts solutions
    if (h.R == h) {cnt++; return;} // if empty: count & done
    DLXNodeHeinz c = h.R; // choose next column c
    cover(c); // remove c from columns
    for (DLXNodeHeinz r = c.D; r!=c; r=r.D){ // forall rows with 1 in c
      for (DLXNodeHeinz j = r.R; j!=r; j=j.R) // forall 1-elements in row
        cover(j.C); // remove column
      search(k+1); // recursion
      for (DLXNodeHeinz j = r.L; j!=r; j=j.L) // forall 1-elements in row
        uncover(j.C); // backtrack: un-remove
    }
    uncover(c); // un-remove c to columns
  }

  public static void cover (DLXNodeHeinz c){ // remove column c
    c.R.L = c.L ; // remove header
    c.L.R = c.R ; // .. from row list
    for (DLXNodeHeinz i = c.D; i!=c; i=i.D) // forall rows with 1
      for (DLXNodeHeinz j = i.R; i!=j; j=j.R){ // forall elem in row
        j.D.U = j.U ; // remove row element
        j.U.D = j.D ; // .. from column list
      }
  }
  public static void uncover (DLXNodeHeinz c){//undo remove col c
    for (DLXNodeHeinz i = c.U; i!=c; i=i.U) // forall rows with 1
      for (DLXNodeHeinz j = i.L; i!=j; j=j.L){ // forall elem in row
        j.D.U = j ; // un-remove row elem
        j.U.D = j ; // .. to column list
      }
    c.R.L = c ; // un-remove header
    c.L.R = c ; // .. to row list
  }

}
