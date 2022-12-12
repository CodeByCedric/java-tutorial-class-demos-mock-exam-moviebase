package util;

import org.jasypt.util.text.StrongTextEncryptor;
import org.mindrot.jbcrypt.BCrypt;

public class Crypto {

    private final StrongTextEncryptor encryptor = new StrongTextEncryptor();
    private final static String KEY = "HELLO-FROM-HOWEST";

    private final static Crypto INSTANCE = new Crypto();

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
