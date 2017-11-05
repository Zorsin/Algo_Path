import java.util.ArrayList;

/**
 * @author Micha Heiß
 */
public class PoliMulti {

  public static void main(String[] args) {
    int [] a = {1, -5, 3, -4};
    int [] b = {4, -3, -2, 1};

    int [] res = prod(a,b);
    printArr(res);

  }

  public static int[] prod (int[] a, int[] b) {
    int   n  = a.length,                 // Problemgroesse
        nh = n/2;                      // halbe Problemgroesse
    int[] r  = new int [2*n];            // Ergebnisarray
    if (n==1) {                          // Kleines Teilproblem:
      r[0] = a[0] * b[0];               //    Direkte Loesung
    } else {                             // sonst:
      int[] al  = new int [nh], ar  = new int [nh],    // *******
          bl  = new int [nh], br  = new int [nh],    // *     *
          alr = new int [nh], blr = new int [nh];    // *     *
      for (int i=0; i<nh; i++){                // *********     *
        alr [i]  = al [i] = a [i];            // *             *
        blr [i]  = bl [i] = b [i];            // *             *
        alr [i] += ar [i] = a [i+nh];         // *   DIVIDE    *
        blr [i] += br [i] = b [i+nh];         // *             *
      } // ********************************    // ***************
      int[] A = prod (al,  bl); // ********    // ***************
      printArr(A);
      int[] B = prod (ar,  br);                // *   CONQUER   *
      printArr(B);
      int[] C = prod (alr, blr); // *******    // ***************
      printArr(C);
      for (int i=0; i<n; i++) { // ********    // ***************
        r [i]    += A [i];                    // *             *
        r [i+nh] += C [i] - A [i] - B [i];    // *   MERGE     *
        r [i+n]   = B [i];                    // *             *
      } // ********************************    // ***************
    }
    return r;
  }

  public static void printArr(int[] a){
    System.out.print("[");
    for(int i : a){
      System.out.print(i + ", ");
    }
    System.out.println("]");
  }

}
