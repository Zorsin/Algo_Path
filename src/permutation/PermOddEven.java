package permutation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Micha Heiß
 */
public class PermOddEven {

  private static int n = 0;

  public static void main(String[] args) {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
      Perm p = new Perm(n);

      int[] tmp = p.getNext();

      int anzahl = 0;
      do{
        System.out.print("[");
        for(int i = 0; i<tmp.length; i++){
          System.out.print(tmp[i]);
          if (i!=tmp.length-1) {
            System.out.print(", ");
          }
        }
        System.out.println("]");
        tmp = p.getNext();
        anzahl++;
      }while(tmp != null);
      System.out.println("es gab genau " + anzahl + " Permutationen der verlangten Art.");
    } catch (Exception e) {
//            e.printStackTrace();
      System.out.println("dieses n ist keine Zahl.");
    }


  }

}
