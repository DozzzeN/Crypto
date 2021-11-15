import java.util.Random;

public class Prf {
    public static void main(String[] args) {
        int temp;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            temp = new Random().nextInt(10000000);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
