package sorting;

/**
 * 17.12.2017
 *
 * @author SWirries
 */
public class quickSort {

  private static int count = 0;

  public static void main(String[] args) {
    char[] chars = new char[]{'b','g','c','h','a','f','j','i','e','d'};
    quickSort(chars);
  }


  static void quickSort (char[] a){ // nichtrekursiver Einstieg
    int hi = a.length-1;
    quickSort(a, 0, hi); // Aufruf der rekursiven Variante
  } // quickSort

  private static void quickSort (char[] a, int lo, int hi){
    int li = lo;
    int re = hi;
    int mid = (li+re)/2;
    if (a[li] > a[mid]) swap(a, li, mid); // Kleines Problem
    if (a[mid] > a[re] ) swap(a, mid, re); // und Vorsortieren
    if (a[li] > a[mid]) swap(a, li, mid); // per Bubble Sort
    if ((re - li) > 2){ // Grosses Problem:
      char w = a[mid]; // Divide:
      do{ while (a[li] < w) li++; // suche von li
        while (a[re] > w) re--; // suche von re
        if (li <= re) swap (a, li++, re--); // vertausche
      } while (li <= re); // bis fertig
      count++;
      printCharArray(a);
      if (lo < re) quickSort (a, lo, re); // Conquer links
      if (li < hi) quickSort (a, li, hi); // Conquer rechts
    } // Merge unnoetig
  } // quickSort


  private static void swap(char[] a, int lo, int hi){
    char tmp = a[lo];
    a[lo] = a[hi];
    a[hi] =tmp;
  }

  private static void printCharArray(char[] a){
    for (int i = 0; i < a.length; i++) {
        if(i == 0){
          System.out.print("Count: "+count + " "+ a[i]);
        }else {
          System.out.print(","+a[i]);
        }
    }
    System.out.println("\n");
  }

}
