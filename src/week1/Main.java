package week1;


import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
/*
    public static void main(String[] args) {
	    int N = StdIn.readInt();
//        QuickFindUF uf = new QuickFindUF(N);
//        QuickUnionUF uf = new QuickUnionUF(N);
//        WeightQUUF uf = new WeightQUUF(N);
        WQUPCUF uf = new WQUPCUF(N);
        while (!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!uf.connected(p,q)){
                uf.union(p,q);
                StdOut.println(p+" "+q);
            }
        }
        System.out.println(uf);
    }
 */
    public static void main(String[] args) throws IOException {
        File inFile = new File ("./txt/Question2_1.txt");

        Scanner sc = new Scanner (inFile);
        int N  = Integer.parseInt(sc.nextLine());
 //       QuickFindUF uf = new QuickFindUF(N);
        WeightQUUF uf = new WeightQUUF(N);
        while (sc.hasNextLine())
        {
            String line = sc.nextLine();
            String[] words = line.split("[\\s]");
//            System.out.println (words[0]);
            int p = Integer.parseInt(words[0]);
            int q = Integer.parseInt(words[1]);
            if (!uf.connected(p,q)){
                uf.union(p,q);
                System.out.println(p+" "+q);
//                System.out.println(uf);
            }
        }
        sc.close();
        System.out.println(uf);
    }
}
