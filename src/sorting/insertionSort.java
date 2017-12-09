package sorting;

/**
 * @author Micha Heiß
 */
public class insertionSort {

  public static int comparisons = 0;
  public static int moves = 0;

  public static void main(String[] args) {

    for(int n = 1; n<13; n++){
      char[] data = new char[n];

      // initialize char array with permutations
      for(int i = data.length; i>0; i--){
        data[data.length-i] = (i + "").toCharArray()[0];
      }

      insertionSort(data);

      System.out.println("n=" + n + " vergleiche: " + comparisons + " moves: " + moves);
    }

    System.out.println();

    for(int n = 1; n<13; n++){
      char[] data = new char[n];

      // initialize char array with permutations
      for(int i = 0; i<data.length; i++){
        data[i] = (i + "").toCharArray()[0];
      }

      insertionSort(data);

      System.out.println("n=" + n + " vergleiche: " + comparisons + " moves: " + moves);
    }


  }


  public static void insertionSort (char[] a){
    int i, hi = a.length-1;
    for (int k = 1; k <= hi; k++) {
      comparisons++;
      if (a[k - 1] > a[k]) { // nur wenn noetig
        char x = a[k];
        a[k] = a[k - 1];
        moves++;
        for (i = k - 1; (i > 0) && (a[i - 1] > x); i--) {
          moves++;
          a[i] = a[i - 1]; // rechts schieben
        }
        a[i] = x; // einfuegen
      }
    }
  } // insertionSort

}
