package week1;


import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class Main {

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
}
