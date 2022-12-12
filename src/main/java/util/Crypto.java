package util;

import org.jasypt.util.text.StrongTextEncryptor;
import org.mindrot.jbcrypt.BCrypt;

public class Crypto {

    private static final String KEY = "HELLO-FROM-HOWEST";
    private static final Crypto INSTANCE = new Crypto();

    private final StrongTextEncryptor encryptor = new StrongTextEncryptor();

    private Crypto() {
        encryptor.setPassword(KEY);
    }

    public String encrypt(String in) {
        return encryptor.encrypt(in);
    }

    public String decrypt(String in) {
        return encryptor.decrypt(in);
    }

    public String hash(String in) {
        return BCrypt.hashpw(in, BCrypt.gensalt());
    }

    public boolean compare(String plainText, String hash) {
        return BCrypt.checkpw(plainText, hash);
    }

    public static Crypto getInstance() {
        return INSTANCE;
    }

}
