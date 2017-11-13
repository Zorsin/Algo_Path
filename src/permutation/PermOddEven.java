package permutation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author MHeiss, SWirries
 */
public class PermOddEven {

  private static int n = 0;

  public static void main(String[] args) {
    //liest eine Zahl ein
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
      //wenn der Cast auf einen Interger funktioniert
      n = Integer.parseInt(input);
      Perm p = new Perm(n);//Starten der Permutation

      //Gibt die Permutationen aus
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
      }while(tmp != null);//bis alle ausgegeben wurden
      System.out.println("Es gab genau " + anzahl + " Permutationen der verlangten Art.");
    } catch (Exception e) {
//            e.printStackTrace();
      //wenn der Cast nicht funktioniert hat
      System.out.println("Dieses n ist keine Zahl.");
    }


  }

}
