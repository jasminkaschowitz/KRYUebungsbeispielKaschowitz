import org.mindrot.jbcrypt.BCrypt;

public class BCryptHash {

        public static String hashWithBcrypt(String password) {
            String salt = BCrypt.gensalt();
            return BCrypt.hashpw(password, salt);
        }
}
