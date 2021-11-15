import java.math.BigInteger;

public class exp {
    public static void main(String[] args) {
//        BigInteger e = new BigInteger("2052765");
//        BigInteger m = new BigInteger("3");
//        System.out.println(m.modPow(e.multiply(BigInteger.valueOf(16)), new BigInteger("36581")));
        BigInteger m1 = new BigInteger("6278");
        BigInteger m2 = new BigInteger("36581");
        BigInteger gcd = m1.gcd(m2);
        System.out.println(gcd);
    }
}
