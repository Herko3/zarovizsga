package hu.nive.ujratervezes.zarovizsga.digitscounter;


import java.util.HashSet;
import java.util.Set;

public class DigitsCounter {

    public int getCountOfDigits(String s) {
        Set<Character> digits = new HashSet<>();
        if (s == null) {
            return 0;
        }

        char[] chars = s.toCharArray();
        for (char c : chars) {
            if(Character.isDigit(c)){
                digits.add(c);
            }
        }
        return digits.size();
    }

}
