import org.mindrot.jbcrypt.BCrypt;

public class BCryptHash {

        public static String hashWithBcrypt(String password) {
            String salt = BCrypt.gensalt();
            return BCrypt.hashpw(password, salt);
        }
        //In dieser Klasse wird der BCRYPT-Hash-Algorithmus verwendet --> zufälliger Salt wird automatisch generiert
}
