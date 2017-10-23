package patternavoiding;

import java.math.BigInteger;

/**
 * @author Micha Heiß
 */
public class PAMain {

  // patterns to be avoided
  private static String[] avoid = new String[]{
      "1000",
      "1011",
      "1111",
      "1101"
  };

  private static int n = 1000;

  public static void main(String[] args) {
//    for(int i = 1;i<=n;i++)
    System.out.println("n=" + n + ", a(n)=" + countPatternsExcept(n, avoid));


  }

  public static BigInteger countPatternsExcept(int n, String[] avoid){

    // no pattern will match if n<4 so just return 2^n
    if(n<4) return new BigInteger((int)(Math.pow(2,n))+"");

    // every possible 3 char pattern a binary string can start with
    String[] startingPattern = new String[]{
        "000",
        "001",
        "010",
        "011",
        "100",
        "101",
        "110",
        "111"
    };
    // array to count for every starting pattern individually
    BigInteger[] numPattern = new BigInteger[]{
        new BigInteger("1"),
        new BigInteger("1"),
        new BigInteger("1"),
        new BigInteger("1"),
        new BigInteger("1"),
        new BigInteger("1"),
        new BigInteger("1"),
        new BigInteger("1")
    };


    // iterate through 3 to n
    for(int i = 3; i<n;i++){

      // array to save new values of numPattern
      BigInteger[] newNumPattern = new BigInteger[]{
          new BigInteger("0"),
          new BigInteger("0"),
          new BigInteger("0"),
          new BigInteger("0"),
          new BigInteger("0"),
          new BigInteger("0"),
          new BigInteger("0"),
          new BigInteger("0")
      };


        int counter = 0;

        // iterate through half of numPattern array while checking for two patterns every iteration
        for(int newPatternUpCount = 0; newPatternUpCount<4; newPatternUpCount++ ){
          boolean found1 = false;
          boolean found2 = false;

          // put 0 and 1 in front of starting pattern
          String tmp0 = "0" + startingPattern[2 * counter];
          String tmp1 = "1" + startingPattern[2 * counter];
          // iterate through strings that have to be avoided
          for(int avoidcount = 0; avoidcount< avoid.length; avoidcount++) {
              if (tmp0.contains(avoid[avoidcount])) {
                found1 = true;
              }
              if (tmp1.contains(avoid[avoidcount])) {
                found2 = true;
              }
          }

          // if all patterns where avoided, add it to newNumPattern at correct position
          if(!found1){
//            newNumPattern[newPatternUpCount] += numPattern[2 * counter];
            newNumPattern[newPatternUpCount] = newNumPattern[newPatternUpCount].add(numPattern[2 * counter]);
          }
          if(!found2) {
//            newNumPattern[4 + newPatternUpCount] += numPattern[2 * counter];
            newNumPattern[4 + newPatternUpCount] = newNumPattern[4 + newPatternUpCount].add(numPattern[2 * counter]);
          }



          found1 = false;
          found2 = false;
          // put 0 and 1 in front of starting pattern
          tmp0 = "0" + startingPattern[1+2*counter];
          tmp1 = "1" + startingPattern[1+2*counter];
          // iterate through strings that have to be avoided
          for(int avoidcount = 0; avoidcount< avoid.length; avoidcount++) {
              if(tmp0.contains(avoid[avoidcount])){
                found1 = true;
              }
              if(tmp1.contains(avoid[avoidcount])){
                found2 = true;
              }
          }

          // if all patterns where avoided, add it to newNumPattern at correct position
          if(!found1){
//            newNumPattern[newPatternUpCount] += numPattern[1+2*counter];
            newNumPattern[newPatternUpCount] = newNumPattern[newPatternUpCount].add(numPattern[1 + 2 * counter]);
          }
          if(!found2) {
//            newNumPattern[4+newPatternUpCount] += numPattern[1+2*counter];
            newNumPattern[4 + newPatternUpCount] = newNumPattern[4 + newPatternUpCount].add(numPattern[1 + 2 * counter]);
          }

          counter++;

        }
      numPattern = newNumPattern;
    }

//    long sum = 0;
    BigInteger sum = new BigInteger("0");
    // add up all numPattern values and return them
    for(BigInteger x : numPattern) sum = sum.add(x);
    return sum;

  }

}
