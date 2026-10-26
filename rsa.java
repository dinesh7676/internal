import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class RSA {
    public static void main(String[] args) {
        SecureRandom rnd = new SecureRandom();
        BigInteger p = BigInteger.probablePrime(512, rnd);
        BigInteger q = BigInteger.probablePrime(512, rnd);
        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger e = BigInteger.valueOf(65537);
        BigInteger d = e.modInverse(phi);

        System.out.print("Enter message: ");
        String msg = new Scanner(System.in).nextLine();

        BigInteger m = new BigInteger(msg.getBytes());
        BigInteger c = m.modPow(e, n);
        BigInteger dec = c.modPow(d, n);

        System.out.println("Ciphertext: " + c);
        System.out.println("Decrypted: " + new String(dec.toByteArray()));
    }
}
