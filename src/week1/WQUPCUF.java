package week1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pavelbolotov on 1/26/16.
 */
public class WQUPCUF {
    private int[] id;
    private int[] sz;

    public WQUPCUF(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++){
            id[i] = i;
            sz[i] = 0;
        }
    }
    private int root (int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
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

    public static void main(String[] args) {
        int N = 10;
        List<int[]> connList = new ArrayList();
        connList.add(new int[]{4,3});
        connList.add(new int[]{3, 8});
        connList.add(new int[]{6, 5});
        connList.add(new int[]{9,4});
        connList.add(new int[]{2,1});
        connList.add(new int[]{8,9});
        connList.add(new int[]{5,0});
        connList.add(new int[]{7,2});
        connList.add(new int[]{6,1});
        connList.add(new int[]{1,0});
        connList.add(new int[]{6,7});

        WQUPCUF uf = new WQUPCUF(N);

        for (int[] iter : connList ){
            int p = iter[0];
            int q = iter[1];
            if (!uf.connected(p,q)){
                uf.union(p,q);
                System.out.println(p+" "+q);
            }
        }
        System.out.println(uf);
    }

}
