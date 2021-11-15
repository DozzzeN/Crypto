import java.util.Random;

public class Additive_Group {
    public static void Add() {
        int[] array1 = new int[Params.TIMES];
        int[] array2 = new int[Params.TIMES];
        for (int i = 0; i < array1.length; i++) {
            array1[i] = new Random().nextInt(Params.TIMES);
            array2[i] = new Random().nextInt(Params.TIMES);
        }
        int temp;
        long start = System.currentTimeMillis();
        for (int i = 0; i < array2.length; i++) {
            temp = array1[i] + array2[i];
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void Mul() {
        int[] array1 = new int[Params.TIMES];
        int[] array2 = new int[Params.TIMES];
        for (int i = 0; i < array1.length; i++) {
            array1[i] = new Random().nextInt(Params.TIMES);
            array2[i] = new Random().nextInt(Params.TIMES);
        }
        int temp;
        long start = System.currentTimeMillis();
        for (int i = 0; i < array2.length; i++) {
            temp = array1[i] * array2[i];
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void main(String[] args) {
        Add();
        Mul();
    }
}
