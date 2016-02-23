package hackerrank;

import java.util.Scanner;

/**
 * Created by SoBoLp on 2/19/16.
 */
public class FunnyString {

    //1st Qustion timeout
    static long kSub(int k, int[] nums) {
        long result = 0;
        for (int j = 0; j<nums.length;j++){
            long sum = 0;
            for (int i = j; i<nums.length;i++){
                sum +=nums[i];
                if (sum%k ==0)
                    result ++;
            }

        }
        return result;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String S = in.nextLine();

        String str = "ababaa";

 //           for(String str:inputs){
                for (int i = 1; i < str.length()-1;i++) {
                    String pref = str.substring(0, i);
                    System.out.println(str.substring(pref.length(), pref.length()+pref.length()));
                    if (str.substring(i, pref.length()+i).compareTo(pref) == 0)

                        System.out.println (pref.length());

                }
  //          }


//
//
//        boolean isNotFunny = false;
//        for (int i = 1; i < S.length(); i++) {
//            int code = Character.codePointAt(S.toCharArray(), i);
//            int valS = Math.abs(Character.codePointAt(S.toCharArray(), i) - Character.codePointAt(S.toCharArray(), i - 1));
//            int valR = Math.abs(Character.codePointAt(S.toCharArray(), S.length() - i - 1) - Character.codePointAt(S.toCharArray(), S.length() - i));
//            if (valS != valR) {
//                isNotFunny = true;
//                break;
//            }
//        }
//        if (isNotFunny)
//            System.out.println("Not Funny");
//        else System.out.println("Funny");
//        Integer test = 1;
//        System.out.println(Integer.toBinaryString(34234));
    }
}
