package power;

/**
 * @author Micha Heiß
 */
public class Power {

    public static int count = 0;

    public static void main(String[] args) {

        double x = 10;
        long n = -1;

        System.out.println(power(x,n));
        System.out.println(count);

    }

    private static double power(double x, long n) {

        if(n<0) {
            count++;
            count++;
            double tmp = power(x, n*-1);
            return 1d / tmp;
        }else if(n<=1){
            return x;
        }else if(n%2 ==1){
            count++;
            count++;
            count++;
            double tmp = power(x, (n-1)/2);
            return x *tmp*tmp;
        }else {
            count++;
            count++;
            double tmp = power(x, n/2);
            return tmp * tmp;
        }

    }

}
