import java.math.BigInteger;
import java.util.HashMap;

import static java.math.BigInteger.valueOf;

/**
 * @author Micha Heiß
 */
public class Munz {

//  static BigInteger[] F = new BigInteger[101]; // table
//  public static BigInteger F (int n){          // method
//    return n<=1         ? valueOf(n)     :    // base case
//        F[n] != null ? F[n]           :    // lookup
//            (F[n] = F(n-1) .add (F(n-2)));     // compute & store
//  }
//
//  static HashMap<Integer,BigInteger> F =
//      new HashMap<Integer,BigInteger>();        // dynamic table
//  public static BigInteger F (int n){          // method
//    if (n<=1)      return valueOf(n);         // base case
//    BigInteger m = F.get(n);                  // lookup
//    if (m != null) return m;                  //   sucessful
//    m = F(n-1) .add (F(n-2));                 // recursion
//    F.put (n,m);                              // store
//    return m;                                 // finish
//  }

  //        Indices       0  1  2   3   4    5    6    7
  static int betrag [] = {1,1,2,2,5,5,15};
  static int n = betrag.length;           // Anzahl Muenzen
  // # Wechselarten fuer Betrag g und Muenzen mit Indices <= i
  static long w [][];                     // Tabelle
  static long w (int g, int i){           // Methode
    return  g <  0         ?  0                           :
        i == 0         ?  (g % betrag[0]==0 ? 1 : 0)  :
            w [g][i] != 0  ?  w [g][i]                    :
                (w [g][i]  = w (g, i-1) + w (g-betrag[i], i)) ;
  }
  public static void main (String[] args) {
    int g = Integer.parseInt(args[0]);  // g lesen
    w = new long[g + 1][n];               // w dimensionieren
    System.out.println("Den Betrag " + g + " kann man auf " +
        w(g, n - 1) + " verschiedene Arten wecheln.");
  }

  }
