import java.util.Random;

public class XOR {
    public static void main(String[] args) {
        int[] array1 = new int[10000000];
        int[] array2 = new int[10000000];
        for (int i = 0; i < 10000000; i++) {
            array1[i] = new Random().nextInt(10000000);
            array2[i] = new Random().nextInt(10000000);
        }
        int temp;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            temp = array1[i] ^ array2[i];
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
