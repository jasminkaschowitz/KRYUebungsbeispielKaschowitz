import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Hash {

    public static String hashWithSHA256(String password) throws NoSuchAlgorithmException {

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());


        return bytesToHex(hash);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
