import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PasswordHasher {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        String filePath = "src/main/resources/best110.txt";
        List<String> words = readWordsFromFile(filePath); // Pfade können an deine Projektstruktur angepasst werden

        List<String> hashedWords = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Geben Sie den Hashvalue ein: ");
        String targetHash = scanner.nextLine();

        //String targetHash = "340d600392818df2413382dc7d8325c360d83ea49a262d31760348484bbc10b5";
        String foundWord = null;

        for (String word : words) {
            //System.out.println("Wort: " + word);
            String md5Hash = MD5Hash.hashWithMD5(word);
            if (md5Hash.equals(targetHash)) {
                foundWord = word;
                break;
            }


            String sha256Hash = SHA256Hash.hashWithSHA256(word);
            if (sha256Hash.equals(targetHash)) {
                foundWord = word;
                break;
            }

            String sha256SaltedHash = SHA256SaltHash.hashWithSHA256AndSalt(word);
            if (sha256SaltedHash.equals(targetHash)) {
                foundWord = word;
                break;
            }

            String bcryptHash = BCryptHash.hashWithBcrypt(word);
            if (bcryptHash.equals(targetHash)) {
                foundWord = word;
                break;
            }
        }

        if (foundWord != null) {
            System.out.println("Gefundenes Wort für den Hash: " + foundWord);
        } else {
            System.out.println("Hash nicht gefunden.");
        }


        // Jetzt enthält "hashedWords" alle gehashten Werte für die Wörter aus der Datei.

    }
        private static List<String> readWordsFromFile (String filePath) throws IOException {
            List<String> words = Files.readAllLines(Paths.get(filePath));
            return words;
        }
    }

