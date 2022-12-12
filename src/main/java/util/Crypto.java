package util;

import org.jasypt.util.text.StrongTextEncryptor;

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

    public static Crypto getInstance() {
        return INSTANCE;
    }

}
