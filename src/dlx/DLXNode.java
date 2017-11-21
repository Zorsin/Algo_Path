package dlx;

/**
 * 21.11.2017
 *
 * @author SWirries
 * <p>
 * This code is
 * documentation enough
 */
public class DLXNode {

  DLXNode header;
  DLXNode left, right, up, down;


  static void cover(DLXNode c){
    c.right.left = c.left ; // remove header
    c.left.right = c.right ; // .. from row list
    for (DLXNode i = c.down; i!=c; i=i.down) // forall rows with 1
      for (DLXNode j = i.right; i!=j; j=j.right){ // forall elem in row
        j.down.up = j.up ; // remove row element
        j.up.down = j.down ; // .. from column list
      }
  }

  static void uncover (DLXNode c){//undo remove col c
    for (DLXNode i = c.up; i!=c; i=i.up) // forall rows with 1
      for (DLXNode j = i.left; i!=j; j=j.left){ // forall elem in row
        j.down.up = j ; // un-remove row elem
        j.up.down = j ; // .. to column list
      }
    c.right.left = c ; // un-remove header
    c.left.right = c ; // .. to row list
  }
}
