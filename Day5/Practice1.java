/**
 * Practice1
 */
public class Practice1 {

    public static void main(String[] args) {
        String c = "";
        int a = (int) c;
        double[][] a = new double[2][3];
        System.out.println(a.length+"  "+a[0].length);
        int count = 0;
        int n =100000;
        if (n % 2 != 0) {
            // System.out.println(n + ": 1");
            while (n > 1) {
                if (n % 2 == 0) {
                    n = n / 2;
                    count++;
                } else {
                    n = (3 * n) + 1;
                    count++;

                }

            }
            System.out.println(count);

        }
    }
}