package patternavoiding;

/**
 * @author Micha Heiß
 */
public class PAMain {

  private static String[] avoid = new String[]{
      "1000",
      "1011",
      "1111",
      "1101"
  };

  private static int n = 100;

  public static void main(String[] args) {
    System.out.println(countPatternsExcept(n, avoid));
  }

  public static long countPatternsExcept(int n, String[] avoid){

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
    long[] numPattern = new long[]{
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1
    };


    for(int i = 3; i<n;i++){

      long[] newNumPattern = new long[]{
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0,
          0
      };


        int counter = 0;

        for(int newPatternUpCount = 0; newPatternUpCount<4; newPatternUpCount++ ){
          boolean found1 = false;
          boolean found2 = false;
          for(int avoidcount = 0; avoidcount< avoid.length; avoidcount++) {


            {
              String tmp0 = "0" + startingPattern[2 * counter];
              if (tmp0.contains(avoid[avoidcount])) {
                found1 = true;
              }

              String tmp1 = "1" + startingPattern[2 * counter];
              if (tmp1.contains(avoid[avoidcount])) {
                found2 = true;
              }
            }

          }

          if(!found1){
            newNumPattern[newPatternUpCount] += numPattern[2 * counter];
          }
          if(!found2) {
            newNumPattern[4 + newPatternUpCount] += numPattern[2 * counter];
          }

          found1 = false;
          found2 = false;

          for(int avoidcount = 0; avoidcount< avoid.length; avoidcount++) {

            {
              String tmp0 = "0" + startingPattern[1+2*counter];
              if(tmp0.contains(avoid[avoidcount])){
                found1 = true;
              }

              String tmp1 = "1" + startingPattern[1+2*counter];
              if(tmp1.contains(avoid[avoidcount])){
                found2 = true;
              }
            }

          }

          if(!found1){
            newNumPattern[newPatternUpCount] += numPattern[1+2*counter];
          }
          if(!found2) {
            newNumPattern[4+newPatternUpCount] += numPattern[1+2*counter];
          }

          counter++;

        }
      numPattern = newNumPattern;
      }

    long sum = 0;
    for(long x : numPattern) sum += x;
    return sum;

  }

}
