package hackerrank;

import java.util.Scanner;

/**
 * Created by SoBoLp on 2/19/16.
 */
public class Pangrams {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String inputs = in.nextLine();
        boolean[] sent = new boolean[26];
//        String inputs = "We promptly judged antique ivory buckles for the next prize";
        char[] chars = inputs.toUpperCase().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int code = Character.codePointAt(chars, i);
            if (Character.isAlphabetic(code))
                if (!sent[code - 65])
                    sent[code - 65] = true;
        }
        int count = 0;
        for (boolean ch : sent) {
            if (ch)
                count++;
        }
        if (count == 26)
            System.out.println("pangram");
        else
            System.out.println("not pangram");
    }

}
//A-65
//Z-90
//a-97
//z-122