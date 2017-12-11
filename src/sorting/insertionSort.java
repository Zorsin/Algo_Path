package sorting;

import permutation.Perm;

/**
 * @author Micha Heiß
 */
public class insertionSort {

  public static int comparisons = 0;
  public static int moves = 0;

  public static int callInsertionSort = 0;
  public static int shifts = 0;

  public static void main(String[] args) {

//    for(int n = 1; n<15; n++){
//      char[] data = new char[n];
//
//      // initialize char array with permutations
//      for(int i = data.length; i>0; i--){
//        data[data.length-i] = (i + "").toCharArray()[0];
//      }

//      insertionSort(data);

//      System.out.println("n=" + n + " vergleiche: " + comparisons + " moves: " + moves);
//    }

//    System.out.println();
//
//    for(int n = 1; n<13; n++){
//      char[] data = new char[n];
//
//      // initialize char array with permutations
//      for(int i = 0; i<data.length; i++){
//        data[i] = (i + "").toCharArray()[0];
//      }
//
//      insertionSort(data);
//
//      System.out.println("n=" + n + " vergleiche: " + comparisons + " moves: " + moves);
//      for(char c : data){
//        System.out.print(""+c);
//      }
//    }
//    char[] a = "krfsmyglfmmoxaxfjbrltjzmkhbjysdjitmulvpmhhaysmddtlbzxwvaacnkdmopqqwneavejagfeozzcoltxrijdghsthvjluhi".toCharArray();
//    shellSort(a);
//    insertionSorth(a,40);
//    for(char c : a){
//      System.out.print(""+c);
//    }
//    System.out.println("");
//    System.out.println(callInsertionSort);
//    System.out.println(shifts);


//    for(int n = 1;n<5;n++){
      int n=5;
      Perm p = new Perm(n);//Starten der Permutation

      //Gibt die Permutationen aus
      int[] tmp = p.getNext();
      int anzahl = 0;
      do{
//        comparisons = moves = 0;
//        for(int c : tmp){
//            System.out.print(" "+c);
//          }
        insertionSort(tmp);
        System.out.println("n=" + n + " vergleiche: " + comparisons + " moves: " + moves);
        anzahl++;
        tmp = p.getNext();


      }while(tmp != null);//bis alle ausgegeben wurden
//    }
    System.out.println(anzahl);
  }


  public static void insertionSort (int[] a){
    int i, hi = a.length-1;
    for (int k = 1; k <= hi; k++) {
      comparisons++;
      if (a[k - 1] > a[k]) { // nur wenn noetig
        int x = a[k];
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

  static void shellSort (char[] a){
    int h, hmax, hi = a.length-1;
// hmax = 1, 4, 13, 40, 121, 364, 1093 .., 3*hmax+1
    for (hmax = 1; hmax < hi ; hmax = 3*hmax+1 );
    System.out.println(hmax/3);
    for ( h = hmax/3; h > 0; h /= 3){
      callInsertionSort++;
      insertionSorth (a, h);
    }
  } // shellSort

  private static void insertionSorth (char[] a, int h){
    int i, hi = a.length-1;
    for (int k = h; k <= hi; k++) // Start bei h
      if (a[k-h] > a[k]){ // Vergleich ueber Distanz h
        char x = a[k];
        a[k] = a[k-h];
        for ( i = k-h; (i >= h) && (a[i-h] > x) ; i-=h ){
          a[i] = a[i-h]; // Transport ueber Distanz h
          shifts++;
        }
        a[i] = x; // einfuegen
      }
  } // insertionSorth

}
