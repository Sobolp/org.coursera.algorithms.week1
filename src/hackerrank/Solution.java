package hackerrank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by SoBoLp on 2/13/16.
 */
public class Solution {
    static class myRegex{
            public String pattern ="^[a-zA-Z][\\w]{7,29}$";
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        Scanner sc = null;
        File inFile = new File("./txt/hackerrank_1.txt");
        try {
            sc = new Scanner(inFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String IP = sc.next();
            System.out.println(IP.matches(new myRegex().pattern));
        }
    }
}
