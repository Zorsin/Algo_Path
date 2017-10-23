package patternavoiding;

/**
 * 10.10.2017
 *
 * @author SWirries
 */
public class StartPatternAvoiding {
/**
 * 0000
 * 0001
 * 0010
 * 0011
 * 0100
 * 0101
 * 0110
 * 0111
 * 1000X
 * 1001
 * 1010
 * 1011X
 * 1100
 * 1101X
 * 1110
 * 1111X
 */
    public static void main(String[] args) {
        PatternAvoiding patternAvoiding = new PatternAvoiding(10000000);
        patternAvoiding.avoidPatten(new String[]{"1111","1000","1011","1101"});
//        String bin = "1010";
//        System.out.println(bin.substring(0,bin.length()-1));
    }

}
