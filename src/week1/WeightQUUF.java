package week1;

import java.io.*;
import java.util.Scanner;


/**
 * Created by pavelbolotov on 1/26/16.
 */
public class WeightQUUF {
    private int[] id;
    private int[] sz;

    public WeightQUUF(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++){
            id[i] = i;
            sz[i] = 0;
        }
    }
    private int root (int i) {
        while (i != id[i])
            i = id[i];
        return i;
    }

    void union(int p, int q){
        int i = root(p);
        int j = root(q);
        if (i == j)
            return;
        if (sz[i] < sz[j]){
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
    }

    boolean connected(int p, int q){
        return root(p) == root(q);
    }

    @Override
    public String toString() {
        String resalt = "{ ";
        for (int i : id){
            resalt +=i+" ";
        }
        resalt +="}";
        return resalt;
    }
    public static void main(String[] args) throws IOException {
        File inFile = new File ("./txt/Question2.txt");

        Scanner sc = new Scanner (inFile);
        int N  = Integer.parseInt(sc.nextLine());
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
                System.out.println(uf);
            }
        }
        sc.close();
        System.out.println(uf);
    }

}
