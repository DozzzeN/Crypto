import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.math.ec.ECPoint;

import java.math.BigInteger;
import java.security.SecureRandom;

public class ESDCA_Group {
    public static void Add() {
        // 曲线参数
        X9ECParameters ecp = SECNamedCurves.getByName("secp256r1");
        ECDomainParameters domainParameters = new ECDomainParameters(ecp.getCurve(), ecp.getG(), ecp.getN(), ecp.getH(), ecp.getSeed());

        // 生成公私钥对
        AsymmetricCipherKeyPair keyPair;
        ECKeyGenerationParameters keyGenerationParameters = new ECKeyGenerationParameters(domainParameters, new SecureRandom());
        ECKeyPairGenerator generator = new ECKeyPairGenerator();
        generator.init(keyGenerationParameters);
        keyPair = generator.generateKeyPair();

        ECPrivateKeyParameters privateKeyParameters = (ECPrivateKeyParameters) keyPair.getPrivate();
        BigInteger privateKey = privateKeyParameters.getD();

//        System.out.println("privateKey： "+privateKey.toString(16));

        ECPoint Q1 = keyGenerationParameters.getDomainParameters().getG();
        ECPoint Q2 = keyGenerationParameters.getDomainParameters().getG();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            ECPoint Q = Q1.add(Q2);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
//        System.out.println(" multiply x: " + Q.getXCoord().toBigInteger().toString(16));
//        System.out.println(" multiply y: " + Q.getYCoord().toBigInteger().toString(16));

    }

    public static void Mul() {
        // 曲线参数
        X9ECParameters ecp = SECNamedCurves.getByName("secp256r1");
        ECDomainParameters domainParameters = new ECDomainParameters(ecp.getCurve(), ecp.getG(), ecp.getN(), ecp.getH(), ecp.getSeed());

        // 生成公私钥对
        AsymmetricCipherKeyPair keyPair;
        ECKeyGenerationParameters keyGenerationParameters = new ECKeyGenerationParameters(domainParameters, new SecureRandom());
        ECKeyPairGenerator generator = new ECKeyPairGenerator();
        generator.init(keyGenerationParameters);
        keyPair = generator.generateKeyPair();

        ECPrivateKeyParameters privateKeyParameters = (ECPrivateKeyParameters) keyPair.getPrivate();

        BigInteger privateKey = privateKeyParameters.getD();

//        System.out.println("privateKey： "+privateKey.toString(16));
        ECPoint Q = keyGenerationParameters.getDomainParameters().getG();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            ECPoint R = Q.multiply(privateKey);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
//        System.out.println(" multiply x: " + Q.getXCoord().toBigInteger().toString(16));
//        System.out.println(" multiply y: " + Q.getYCoord().toBigInteger().toString(16));

    }

    public static void main(String[] args) {
        Add();
        Mul();
    }
}
