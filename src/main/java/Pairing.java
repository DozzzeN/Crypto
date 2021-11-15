//import it.unisa.dia.gas.jpbc.Element;
//import it.unisa.dia.gas.jpbc.PairingParameters;
//import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
//import it.unisa.dia.gas.plaf.jpbc.pairing.a.TypeACurveGenerator;
//
//public class Pairing {
//    public static void main(String[] args) {
//        // rBit是Zp中阶数p的比特长度；qBit是G中阶数的比特长度
//        TypeACurveGenerator pg = new TypeACurveGenerator(512, 512);
//        PairingParameters typeAParams = pg.generate();
//        it.unisa.dia.gas.jpbc.Pairing pairing = PairingFactory.getPairing(typeAParams);
//        Element P = pairing.getG1().newRandomElement().getImmutable();
//        Element Q = pairing.getG1().newRandomElement().getImmutable();
//        long time1 = System.currentTimeMillis();
//        for (int i = 0; i < 100; i++) {
//            pairing.pairing(P, Q);
//        }
//        long time2 = System.currentTimeMillis();
//        System.out.println(time2 - time1);
//    }
//}
