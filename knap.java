import java.math.BigInteger;
import java.util.Scanner;

public class MerkleHellman {
    public static void main(String[] args) {
        int[] w = {2, 7, 11, 21, 42, 89, 180, 354};
        int q = 881, r = 588, n = w.length;

        int[] pub = new int[n];
        for (int i = 0; i < n; i++) pub[i] = w[i] * r % q;
        int rInv = BigInteger.valueOf(r).modInverse(BigInteger.valueOf(q)).intValue();

        System.out.print("Enter message: ");
        String msg = new Scanner(System.in).nextLine();

        StringBuilder cipher = new StringBuilder(), plain = new StringBuilder();
        for (char ch : msg.toCharArray()) {
            int c = 0;
            for (int i = 0; i < n; i++)
                if ((ch >> (n - 1 - i) & 1) == 1) c += pub[i];
            cipher.append(c).append(' ');

            int s = c * rInv % q, bits = 0;
            for (int i = n - 1; i >= 0; i--)
                if (w[i] <= s) { s -= w[i]; bits |= 1 << (n - 1 - i); }
            plain.append((char) bits);
        }
        System.out.println("Ciphertext: " + cipher);
        System.out.println("Decrypted: " + plain);
    }
}
