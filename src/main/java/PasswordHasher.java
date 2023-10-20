import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class PasswordHasher {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        String filePath = "src/main/resources/best110.txt";
        System.out.print("Bitte geben Sie den Hash-Wert ein: ");
        Scanner scanner = new Scanner(System.in);
        String targetHash = scanner.nextLine();

        List<String> words = readWordsFromFile(filePath);

        //Map<String, String> rainbowTable = generateRainbowTable(words);

        //String foundWord = rainbowTable.get(targetHash);

        Map<String, RainbowTable> rainbowTable = generateRainbowTable(words);

        RainbowTable foundHash = rainbowTable.get(targetHash);

        if (foundHash != null) {
            System.out.println("Gefundenes Wort für den Hash: " + foundHash.getWord());
            System.out.println("Verwendete Hash-Methode: " + foundHash.getHashMethod());
        } else {
            System.out.println("Hash wurde nicht gefunden.");

        }
    }

    private static Map<String, RainbowTable> generateRainbowTable(List<String> words) throws NoSuchAlgorithmException {
        Map<String, RainbowTable> rainbowTableHash = new HashMap<>();

        for (String word : words) {
            RainbowTable md5Hash = new RainbowTable(MD5Hash.hashWithMD5(word), "MD5", word);
            RainbowTable sha256Hash = new RainbowTable(SHA256Hash.hashWithSHA256(word), "SHA256", word);
            RainbowTable sha256SaltedHash = new RainbowTable(SHA256SaltHash.hashWithSHA256AndSalt(word), "HA256 with Salt", word);
            RainbowTable bcryptHash = new RainbowTable(BCryptHash.hashWithBcrypt(word), "BCrypt", word);

            // Fügen Sie die Zuordnungen zwischen Hash und Passwort in die Rainbow Table ein
            rainbowTableHash.put(md5Hash.getHash(), md5Hash);
            rainbowTableHash.put(sha256Hash.getHash(), sha256Hash);
            rainbowTableHash.put(sha256SaltedHash.getHash(), sha256SaltedHash);
            rainbowTableHash.put(bcryptHash.getHash(), bcryptHash);
        }

        return rainbowTableHash;
    }

    private static List<String> readWordsFromFile(String filePath) throws IOException {
        List<String> words = Files.readAllLines(Paths.get(filePath));
        return words;
    }
}
    //In dieser überarbeiteten Version wird die Rainbow Table in der Map<String, String> rainbowTable gespeichert, wobei der Hash-Wert als Schlüssel und das ursprüngliche Passwort als Wert dienen. Beachten Sie, dass dies immer noch ein einfaches Beispiel ist, das nicht alle Aspekte einer echten Rainbow-Table-Implementierung berücksichtigt. In der Praxis ist die Erstellung einer Rainbow Table ein sehr ressourcenintensiver und komplexer Prozess.






