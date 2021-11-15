import java.security.MessageDigest;
import java.util.Random;

public class Hash {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        //把密文转换成十六进制的字符串形式
        for (byte aByte : bytes) {
            buf.append(HEX_DIGITS[(aByte >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[aByte & 0x0f]);
        }
        return buf.toString();
    }

    public static String encode(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] encode0(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes());
            return messageDigest.digest();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
//        String[] strings = new String[Params.TIMES];
//        for (int i = 0; i < strings.length; i++) {
//            strings[i] = String.valueOf(new Random().nextInt(Params.TIMES));
//        }
//        long start1 = System.currentTimeMillis();
//        for (String string : strings) {
//            encode0(string);
//        }
//        long end1 = System.currentTimeMillis();
//        System.out.println(end1 - start1);

        //1MB数据
        byte[] b = new byte[1024 * 1024];
        int times = 1000;
        new Random().nextBytes(b);
        String s = getFormattedText(b);
        long start2 = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            encode(s);
        }
        long end2 = System.currentTimeMillis();
        System.out.println((end2 - start2) / times);
    }
}
