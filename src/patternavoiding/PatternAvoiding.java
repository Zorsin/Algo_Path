package patternavoiding;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    /**
     * Zaehlt alle Bit-Strings bis zur Laenge n, die nicht die Uebergebenen Pattern enthalten
     * @param patterns String-Array mit allen Pattern, die nicht enthalten sein sollen.
     */
    public void avoidPatten(String[] patterns){
        /**
         * Speichert die Zeit zu beginn und zum Ende um die Laufzeit ablesen zu koennen
         * hat keinen Einfluss auf das Programm
         */
        LocalTime start = LocalTime.now(); //Start-Zeit des Programms

        /**
         * Hash-Maps mit den Strings und der Anzahl des Vorkommens.
         * im Key-Teil werden die Patterns ohne das erste Zeichen gespeichert.
         * im Value-Teil wird die Anzahl der Strings gespeichert die mit dem Key-Pattern beginnen
         */
        HashMap<String, BigInteger> beginRun = new HashMap<>(); //Map zu Beginn der Iteration
        HashMap<String, BigInteger> endRun = new HashMap<>(); //Map zum Ende der Iteration
        HashMap<String, BigInteger> emptyRun = new HashMap<>(); //Leere Map
        int patternlengh = patterns[0].length();
        /**
         * Fuellt alle Maps mit allen möglichen Anfangsbit-Strings
         */
        for(int i = 0;i<Math.pow(2d,patternlengh-1);i++){
            String binary = Integer.toBinaryString(i);
            while (binary.length()<patterns[0].length()-1){
                binary = "0"+binary;
            }
            beginRun.put(binary,new BigInteger(i<Math.pow(2,n)?"1":"0"));//Fuellt die erste Map mit 1 wenn i<2^n
            endRun.put(binary,new BigInteger("0"));//initialiesiert die Maps
            emptyRun.put(binary,new BigInteger("0"));
        }
        //Erstellt ein Regex mit allen Patterns
        String regex = "";
        for(int i = 0; i<patterns.length;i++){
            if(i==0){
                regex += "("+patterns[i]+")";
            }else{
                regex += "|("+patterns[i]+")";
            }

        }
        /**
         * Iteriert ueber alle n, fuegt den Keys eine Null und eine Eins vorran wenn sie jetzt der Regex entsprechen
         * werden sie verworfen sonst wird der neue beginn ermittelt und weiter gezaehlt
         */
        for(int runs = patternlengh-1;runs<n;runs++) {
            //Fuer jeden Eintrag in der beginnMap
            for (Map.Entry<String, BigInteger> entry : beginRun.entrySet()) {
                String key = entry.getKey();
                BigInteger oldVal = entry.getValue();
                //Fuegt allen Key-Strings in der Map eine Null voran
                String keyWith0 = "0" + key;
                if (!keyWith0.matches(regex)) { //Wenn der neue String nicht der Regex entspricht
                    String newKey = keyWith0.substring(0, patternlengh - 1); //Bildet den neuen Key String
                    BigInteger endValue = endRun.get(newKey);
                    BigInteger newValue = endValue.add(oldVal); //Berechnet die neue Anzahl der vorkommen
                    endRun.remove(newKey); //loescht den alten um duplikate vermeiden
                    endRun.put(newKey, newValue);//fuegt ihn der Map hinzu
                }
                //Fuegt allen Key-Strings in der Map eine eins voran
                String keyWith1 = "1" + key;
                if (!keyWith1.matches(regex)) {
                    String newKey = keyWith1.substring(0, patternlengh - 1);
                    BigInteger endValue = endRun.get(newKey);
                    BigInteger newValue = endValue.add(oldVal);
                    endRun.remove(newKey);
                    endRun.put(newKey, newValue);
                }
            }
            beginRun = new HashMap<>(endRun);//Clont die endMap zur neuen beginnMap
            endRun = new HashMap<>(emptyRun);//Loescht alle eintraege in der endMap
        }
        /**
         * Zaehlt nach der Iteration alle noch vorhanden Strings (Values)
         */
        BigInteger a = new BigInteger("0");
        for(Map.Entry<String, BigInteger> entry : beginRun.entrySet()){
           a = a.add(entry.getValue());
        }

        System.out.println(a.toString());//gibt a(n) aus
        LocalTime end = LocalTime.now();//speichert die End-Zeit
//        System.out.println(beginRun);
        System.out.println(start);//Gibt die Zeiten aus
        System.out.println(end);
    }
}
