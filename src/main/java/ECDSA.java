import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;

import java.nio.charset.StandardCharsets;
import java.security.*;

public class ECDSA {
    public static KeyPair pair;

    static {
        try {
            pair = GenerateKeys();
        } catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
    }

    public static KeyPair GenerateKeys()
            throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {
        Security.addProvider(new BouncyCastleProvider());
        ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("B-571");
        KeyPairGenerator g = KeyPairGenerator.getInstance("ECDSA", "BC");
        g.initialize(ecSpec, new SecureRandom());
        return g.generateKeyPair();
    }

    public static byte[] Sign(String plaintext) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Security.addProvider(new BouncyCastleProvider());
        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA", "BC");
        ecdsaSign.initSign(pair.getPrivate());
        ecdsaSign.update(plaintext.getBytes(StandardCharsets.UTF_8));
        return ecdsaSign.sign();
    }

    public static boolean Verify(String plaintext, byte[] signature) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Security.addProvider(new BouncyCastleProvider());
        Signature ecdsaVerify;
        ecdsaVerify = Signature.getInstance("SHA256withECDSA", "BC");
        ecdsaVerify.initVerify(pair.getPublic());
        ecdsaVerify.update(plaintext.getBytes(StandardCharsets.UTF_8));
        return ecdsaVerify.verify(signature);
    }

    public static void main(String[] args) {
        long time1 = System.currentTimeMillis();
        try {
            System.out.println(Verify("123", Sign("123")));
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
    }
}
