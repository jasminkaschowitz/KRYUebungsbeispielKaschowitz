import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Hash {

    public static String hashWithMD5(String password) throws NoSuchAlgorithmException {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(password.getBytes());

        return bytesToHex(hash);
        /// Diese Methode verwendet den MD5-Hash-Algorithmus, um das eingegebene Passwort zu hashen
    }

    private static String bytesToHex(byte[] bytes) {
        //binäres Hash-Ergebnis in eine Hexadezimaldarstellung umwandeln
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

}
