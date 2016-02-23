package hackerrank;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SoBoLp on 2/20/16.
 */
public class TheFullCountingSort {
//    private static List<Integer> findIndex(int[] input, int value, int count) {
private static List<String> findIndex(int[] input, String[] inStr, int value, int count) {
        int faund = 0;
        int secHalf = 0;
//        List<Integer> result = new ArrayList<Integer>();
        List<String> result = new ArrayList<String>();
        for (int index = 0; index < input.length; index++) {
            if (input[index] == value) {
//                result.add(index);
                result.add(findStr(inStr, index));
                faund++;
                if (index > input.length/2)
                    secHalf++;
                if(secHalf==count)
                    return result;
            }
        }
        return result;
    }

    private static String findStr(String[] inStr, int index) {
        int n = inStr.length;
        if (index < n / 2)
            return "-";
        else
            return inStr[index];
    }

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        String s = in.nextLine();
//        int[] input = new int[n];
//        String[] inStr = new String[n];

        int n = 20;
        int[] input = {0, 6, 0, 6, 4, 0, 6, 0, 6, 0, 4, 3, 0, 1, 5, 1, 2, 4, 2, 4};
        String[] inStr = {"ab", "cd", "ef", "gh", "ij", "ab", "cd", "ef", "gh", "ij", "that", "be", "to", "be", "question", "or", "not", "is", "to", "the"};
        int[] count = new int[100];
        for (int i = 0; i < n; i++) {
//            String line = in.nextLine();
//            String[] elem = line.split("\\s");
//            System.out.println(elem[0]+" - "+elem[1]);
//            input[i] = Integer.parseInt(elem[0]);
//            inStr[i] = elem[1];
//            count[input[i]] ++;
        }

        for (int i = n / 2; i < input.length; i++)
            count[input[i]]++;

//        for (int i = 0; i < count.length; i++)
//            System.out.print(count[i]);
//        System.out.println();

        int printed = 0;
        int i = 0;
        StringBuffer buffer = new StringBuffer();
        while (count[i] > 0){
//        for (int i = 0; i < count.length; i++) {
//            for (Integer st : findIndex(input, i,count[i])) {
//            System.out.print(findStr(inStr, st) + " ");
            for (String st : findIndex(input,inStr, i,count[i])) {
    //            System.out.print( st + " ");
                buffer.append(st+" ");
                printed++;
            }
            i++;
        }
        for (int idx = printed; idx<n;idx++)
            buffer.append("- ");
  //          System.out.print("- ");
        System.out.println(buffer);


    }


}
