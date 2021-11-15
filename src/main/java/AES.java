import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class AES {
    /**
     * AES加密函数
     *
     * @param key:加密密钥,128或256位
     * @param data：要加密的数据
     * @return 密文
     */
    public static byte[] aes_encrypt(byte[] key, byte[] data) {
        String key_algorithm = "AES";

        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES");
            Key key1 = initKeyForAES(new String(key));
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key1.getEncoded(), key_algorithm));
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException | InvalidKeyException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * AES解密函数
     *
     * @param key  密钥
     * @param data 密文
     * @return 明文
     */
    public static byte[] aes_decrypt(byte[] key, byte[] data) {
        String key_algorithm = "AES";
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES");
            Key key1 = initKeyForAES(new String(key));
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key1.getEncoded(), key_algorithm));
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException | NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        int times = Params.TIMES / 1000;
        byte[][] array1 = new byte[times][1];
        byte[][] ciphertext = new byte[times][1];
        byte[][] plain = new byte[times][1];
        for (int i = 0; i < times; i++) {
            array1[i][0] = (byte) new Random().nextInt(times);
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < array1.length; i++) {
            ciphertext[i] = aes_encrypt("123".getBytes(StandardCharsets.UTF_8), array1[i]);
        }
        for (int i = 0; i < array1.length; i++) {
            plain[i] = aes_decrypt("123".getBytes(StandardCharsets.UTF_8), ciphertext[i]);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static Key initKeyForAES(String key) throws NoSuchAlgorithmException {
        if (null == key || key.length() == 0) {
            throw new NullPointerException("key not is null");
        }
        SecretKeySpec key2 = null;
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(key.getBytes());
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, random);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            key2 = new SecretKeySpec(enCodeFormat, "AES");
        } catch (NoSuchAlgorithmException ex) {
            throw new NoSuchAlgorithmException();
        }
        return key2;

    }

}
