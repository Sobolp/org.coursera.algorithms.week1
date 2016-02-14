package hackerrank;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by SoBoLp on 2/13/16.
 */
public class DuplicateWords {

        public static void main(String[] args){

        String pattern ="\\b(\\w+)\\b[\\w\\W]*\\b\\1\\b";
            //"(?i)\\b([a-z]+)\\b(?:\\s+\\1\\b)+"
                Pattern r = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);

        Scanner in = new Scanner(System.in);
//        int testCases = Integer.parseInt(in.nextLine());
        int  testCases = 1;
        while(testCases>0){
//            String input = in.nextLine();
            String input = "Goodbye bye bye world world world";
            Matcher m = r.matcher(input);
            boolean findMatch = true;
            while(m.find( )){
                input = input.replaceAll(m.group(),m.group(1));
                System.out.println(m.group(1));
                findMatch = false;
            }
            System.out.println(input);
            testCases--;
        }
    }


}
