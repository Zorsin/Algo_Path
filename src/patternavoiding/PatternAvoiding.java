package patternavoiding;

import java.util.ArrayList;

/**
 * 10.10.2017
 *
 * @author SWirries
 */
public class PatternAvoiding {
    int n = 0;

    public PatternAvoiding(int n) {
        this.n = n;
    }

    public void generateString(){
        ArrayList<String> binaryStrings = new ArrayList<>();
        int max = (int) Math.pow(2d,n);
        for(int i = 0;i<max;i++){
            String binary = Integer.toBinaryString(i);
            while (binary.length()<n){
                binary = "0"+binary;
            }

            if(!binary.matches("(.*1000.*)|(.*1011.*)|(.*1101.*)|(.*1111.*)")){
                binaryStrings.add(binary);
            }

        }

        for(String s:binaryStrings){
            System.out.println(s);
        }
        System.out.println(binaryStrings.size());
    }
}
