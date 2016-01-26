package week1;

/**
 * Created by pavelbolotov on 1/26/16.
 */
public class QuickFindUF {
    private int[] id;

    public QuickFindUF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++){
            id[i] = i;
        }
    }

    void union(int p, int q){
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++){
            if (id[i] == pid)
                id[i] = qid;
        }
    }

    boolean connected(int p, int q){
        return id[p] == id[q];
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
