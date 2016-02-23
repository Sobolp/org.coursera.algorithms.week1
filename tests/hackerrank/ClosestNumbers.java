package hackerrank;

import java.util.*;

/**
 * Created by SoBoLp on 2/20/16.
 */
public class ClosestNumbers {
    private static class Pair implements Comparable<Pair>{
        Integer A1;
        Integer A2;
        Integer difference;

        public Pair(Integer a1, Integer a2) {
            A1 = a1;
            A2 = a2;
            difference = A1 - A2;
        }

        @Override
        public int compareTo(Pair that) {
            if (that.difference == this.difference)
                return 0;
            if (this.difference<that.difference)
                return -1;
            else return 1;
        }
    }

    public static void main(String[] args) {
        int N = 4;
        List<Integer> A = new ArrayList<>();
        A.addAll(Arrays.asList(5, 4, 3, 2));
        A.sort(Comparator.reverseOrder());
        for (Integer elA:A) {
            System.out.print(elA+" ");
        }
        System.out.println();

        List<Pair> pairs = new ArrayList<>();
        for (int i = 0;i<A.size()-1;i++){
            pairs.add(new Pair(A.get(i),A.get(i+1)));
        }
        pairs.sort(Comparator.naturalOrder());
        Pair min = pairs.get(0);
        //System.out.print (min.A2+" "+min.A2)
//        for (Pair p:pairs){
        for (int i=pairs.size()-1;i>=0;i--){
            Pair p = pairs.get(i);
            if(p.difference == min.difference)
                System.out.print(p.A2+" "+p.A1+" ");
        }

    }
}
