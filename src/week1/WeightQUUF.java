package week1;

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

}
