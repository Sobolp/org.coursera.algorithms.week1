package hackerrank;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by SoBoLp on 2/21/16.
 */
public class JimAndTheOrders {
    private static class Fan implements Comparable<Fan>{
        Integer npp;
        Integer t;
        Integer d;
        Integer sumTime;

        public Fan(Integer npp, Integer t, Integer d) {
            this.npp = npp;
            this.t = t;
            this.d = d;
            sumTime = t+d;
        }

        @Override
        public int compareTo(Fan that) {
            if (this.sumTime == that.sumTime)
                return 0;
            if (this.sumTime < that.sumTime)
                return -1;
            else return 1;
        }
    }
    public static void main(String[] args) {
        int N = 3;
        List<Fan> fanList = new ArrayList<>(3);
        fanList.add(new Fan(1,1,3));
        fanList.add(new Fan(2,2,3));
        fanList.add(new Fan(3,3,3));
        fanList.sort(Comparator.naturalOrder());
        for (Fan f:fanList){
            System.out.print(f.npp+" ");
        }


    }
}
