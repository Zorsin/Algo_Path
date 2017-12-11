package permutation;

import java.util.ArrayList;

/**
 * @author MHeiss SWirries
 */
public class Perm extends Thread {
  private int[] a; // a Arbeitsarray
  private int max; // maximaler Index
  private boolean mayread = false; // Kontrolle
  private ArrayList<Integer> allowed = new ArrayList<>();

  public Perm(int n) { // Konstruktor
    a = new int[n]; // Indices: 0 .. n-1
    max = n-1; // Maximaler Index
    for (int i = 1; i <= n; ) {
      a[i-1] = i++; // a fuellen
    }
    this.start(); // run-Methode beginnt zu laufen
  } // end Konstruktor

  public void run() {// die Arbeits-Methode
    perm(0); // a[0] bleibt fest; permutiere ab 1
    a = null; // Anzeige, dass fertig
    put(); // ausliefern
  } // end run

  public void perm(int i) { // permutiere ab Index i
    if (i >= max) {
      /**
       * Neue Funktion, prueft ob die Permutation den Anforderungen entspricht
       */
//      if(check())
        put(); // eine Permutation fertig
    } else {
      for (int j = i; j <= max; j++) { // jedes nach Vorne
        swap(i, j); // vertauschen
        perm(i + 1); // und Rekursion
      }
      int h = a[i]; // restauriere Array
      System.arraycopy(a, i + 1, a, i, max - i); // shift links
      a[max] = h;
    }
  } // end perm

  private void swap(int i, int j) { // tausche a[i] <-> a[j]
    if (i != j) {

      int h = a[i];
      a[i] = a[j];
      a[j] = h;
    }
  } // end swap

  /**
   * Prueft ob die Permutation den Anforderungem mit der Zahlenreihe erfuellt
   * @return wenn es sich um eine gueltige Permutation handelt
   */
  private boolean check(){
    boolean out = true;
    for(int i =0,j=1;i<=max;i++,j++) {
      if(j>max) break;
      int sum = a[i] + a[j];//berechtung der Summer zweier zahlen
      if (sum % 2 == 0) { // wenn gerade
        // erste Zahl muss größer sein
        if (a[i] < a[j]) {
          out = false;
        }
      } else {//wenn ungerade
        // erste Zahl muss kleiner sein
        if (a[i] > a[j]) {
          out = false;
        }
      }
    }
    return out;
  }

  public synchronized int[] getNext() { // liefert naechste Permutation
    mayread = false; // a darf geaendert werden
    notify(); // wecke anderen Thread
    try {
      while (!mayread) {
        wait(); // non busy waiting
      }
    } catch (InterruptedException e) {
    }
    return a; // liefere Permutationsarray
  } // end getNext

  private synchronized void put() { // uebergebe array
    mayread = true; // a wird gelesen
    notify(); // wecke anderen Thread
    try {
      if (a != null) {
        while (mayread) {
          wait(); // non busy waiting
        }
      }
    } catch (InterruptedException e) {
    }
  } // end put
} // Perm


