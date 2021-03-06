package power;

/**
 * @author Micha Heiß
 */
public class Power {

    public static int count = 0;

    public static void main(String[] args) {

        double x = 2;
        long n = 1024;

        System.out.println(power(x,n));
        System.out.println(count);

    }

    private static double power(double x, long n) {

        if(n<0) {
            count+=2;
            double tmp = power(x, n*-1);
            return 1d / tmp;
        }else if(n<=1){
            return x;
        }else if(n%2 ==1){
            count+=3;
            double tmp = power(x, (n-1)/2);
            return x *tmp*tmp;
        }else {
            count+=2;
            double tmp = power(x, n/2);
            return tmp * tmp;
        }

    }

}
