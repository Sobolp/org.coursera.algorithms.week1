package hackerrank;

/**
 * Created by SoBoLp on 2/18/16.
 */
public class TimeConversion {
    public static void main(String[] args) {
        String time = "12:00:00AM";
        boolean pm = time.endsWith("PM");
        String[] time_split = time.substring(0,8) .split(":");
        String hh ;
        Integer hhI = Integer.parseInt(time_split[0]);
        if (pm) {
            if(hhI != 12) {
                hhI += 12;
                hh = hhI.toString();
            }
            else
                hh = "12";
        }else {
            if (hhI == 12)
                hh = "00";
            else
                hh = time_split[0];
        }
        System.out.printf("%s:%s:%s",hh,time_split[1],time_split[2]);


    }
}
