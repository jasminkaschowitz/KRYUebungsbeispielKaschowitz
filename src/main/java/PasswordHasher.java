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

        Map<String, RainbowTable> rainbowTable = generateRainbowTable(words);
        //zur Vereinfachung wird die Rainbow Table in eine Map gespeichert

        RainbowTable foundHash = rainbowTable.get(targetHash);
        //target Hash wird in der Rainbow Table gesucht

        if (foundHash != null) {
            System.out.println("Gesuchter Hash-Wert lautet: " + foundHash.getHash());
            System.out.println("Gefundenes Wort für den Hash: " + foundHash.getWord());
            System.out.println("Verwendete Hash-Methode: " + foundHash.getHashMethod());

        } else {
            System.out.println("Hash wurde nicht gefunden.");

        }
        //wird Hash-Wert gefunden, wird das ursprüngliche Passwort und die verwendete Hash Methode ausgegeben
    }

    private static Map<String, RainbowTable> generateRainbowTable(List<String> words) throws NoSuchAlgorithmException {
        Map<String, RainbowTable> rainbowTableHash = new HashMap<>();
        //Für jedes Passwort in der Liste wird der Hash-Wert für die veschiedenen Hash-Methoden berechnet
        // Erzeugt die Rainbow Table

        for (String word : words) {
            RainbowTable md5Hash = new RainbowTable(MD5Hash.hashWithMD5(word), "MD5", word);
            RainbowTable sha256Hash = new RainbowTable(SHA256Hash.hashWithSHA256(word), "SHA256", word);
            RainbowTable sha256SaltedHash = new RainbowTable(SHA256SaltHash.hashWithSHA256AndSalt(word), "HA256 with Salt", word);
            RainbowTable bcryptHash = new RainbowTable(BCryptHash.hashWithBcrypt(word), "BCrypt", word);
            //der Hash-Wert des Passworts wird mit dem jeweiligem Algorithmus berechnet und ein neues RainbowTable-Objekt wird erstellt

            rainbowTableHash.put(md5Hash.getHash(), md5Hash);
            rainbowTableHash.put(sha256Hash.getHash(), sha256Hash);
            rainbowTableHash.put(sha256SaltedHash.getHash(), sha256SaltedHash);
            rainbowTableHash.put(bcryptHash.getHash(), bcryptHash);
            //Objekt wird mit dem berechneten Hash-Wert als Schlüssel in die in Map eingefügt
        }

        return rainbowTableHash;

    }

    private static List<String> readWordsFromFile(String filePath) throws IOException {
        List<String> wordList = Files.readAllLines(Paths.get(filePath));
        return wordList;
    }
}






