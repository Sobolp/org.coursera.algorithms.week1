package hackerrank;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by SoBoLp on 2/13/16.
 */
public class TagRemove {
//    (?!<)(?<=>)(.+)(?=</)(?<!>) maybe?
        public static void main(String[] args){

        String pattern ="<(.+>)[^1](.+)</\\1";
            Pattern r = Pattern.compile(pattern);

        Scanner in = new Scanner(System.in);
//        int testCases = Integer.parseInt(in.nextLine());
        int  testCases = 1;
        while(testCases>0){
//            String input = in.nextLine();
            String input = "<h1>had<h1>public</h1></h1>";
            Matcher m = r.matcher(input);
            boolean findMatch = false;

                while(m.find()){
    //                System.out.println(m.group().replaceAll("<"+m.group(1),"").replaceAll("</"+m.group(1),""));
                    int level=2;
                    String oldPattern = pattern;
                    String oldgroup = m.group();
                    String dpattern = makePattern(oldPattern,level);
                    Pattern r2 = Pattern.compile(dpattern);
                    Matcher m2 = r2.matcher(oldgroup);
                    String result =m.group(level);
                    while (m2.find()){
                        level ++;
                        result = m2.group(level);
                        dpattern = makePattern(dpattern,level);
                        r2 = Pattern.compile(dpattern);
                        m2=r2.matcher(m2.group());

                    }
    //                String result = findMatch(m.group());
                    if (!result.matches("(.*)<.+>(.*)")){
                        System.out.println(result);
                        findMatch = true;
                    }

                }
                if (!findMatch)
                    System.out.println("None");
     //           System.out.println(input);
                testCases--;
                }
            }
            private static String makePattern(String old,int level){
                return old.replaceFirst("\\(\\.\\+\\)",".+<(.+>)(.+)</\\\\"+level);
            }

        private static String findMatch(String inp){
            String pattern ="<(.+>)(.+)</\\1";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(inp);
            if (m.find()){
                return findMatch(m.group(2));
            }else {
                return inp;
            }
        }

}
