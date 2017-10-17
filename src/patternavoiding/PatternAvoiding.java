package patternavoiding;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public void buildPattern(String[] patterns){
        HashMap<String, BigInteger> beginRun = new HashMap<>();
        HashMap<String, BigInteger> endRun = new HashMap<>();
        HashMap<String, BigInteger> emptyRun = new HashMap<>();
        int patternlengh = patterns[0].length();
        for(int i = 0;i<Math.pow(2d,patternlengh-1);i++){
            String binary = Integer.toBinaryString(i);
            while (binary.length()<patterns[0].length()-1){
                binary = "0"+binary;
            }
            beginRun.put(binary,new BigInteger(i<Math.pow(2,n)?"1":"0"));
            endRun.put(binary,new BigInteger("0"));
            emptyRun.put(binary,new BigInteger("0"));
        }
        String regex = "";
        for(int i = 0; i<patterns.length;i++){
            if(i==0){
                regex += "("+patterns[i]+")";
            }else{
                regex += "|("+patterns[i]+")";
            }

        }
        for(int runs = patternlengh-1;runs<n;runs++) {

            for (Map.Entry<String, BigInteger> entry : beginRun.entrySet()) {
                String key = entry.getKey();
                BigInteger oldVal = entry.getValue();
                //Add 0
                String keyWith0 = "0" + key;
                if (!keyWith0.matches(regex)) {
                    String newKey = keyWith0.substring(0, patternlengh - 1);
                    BigInteger endValue = endRun.get(newKey);
                    BigInteger newValue = endValue.add(oldVal);
                    endRun.remove(newKey);
                    endRun.put(newKey, newValue);
                }
                //add 1
                String keyWith1 = "1" + key;
                if (!keyWith1.matches(regex)) {
                    String newKey = keyWith1.substring(0, patternlengh - 1);
                    BigInteger endValue = endRun.get(newKey);
                    BigInteger newValue = endValue.add(oldVal);
                    endRun.remove(newKey);
                    endRun.put(newKey, newValue);
                }
            }
            beginRun = new HashMap<>(endRun);
            endRun = new HashMap<>(emptyRun);
//                beginRun = (HashMap<String, Integer>) endRun.clone();


        }
        BigInteger a = new BigInteger("0");
        for(Map.Entry<String, BigInteger> entry : beginRun.entrySet()){
           a = a.add(entry.getValue());
        }

        System.out.println(a.toString());
//        System.out.println(beginRun);

        /**
         * Merke dir verbotene Pattern als 3 Zeichen
         * Setze vor die Pattern eine 1 o 0 wenn verboten dann filter sonst ersten 3 merken
         */
    }
}
