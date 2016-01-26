package week1;

/**
 * Created by pavelbolotov on 1/26/16.
 */
public class QuickUnionUF {
    private int[] id;

    public QuickUnionUF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++){
            id[i] = i;
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
        id[i] = j;
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

}
