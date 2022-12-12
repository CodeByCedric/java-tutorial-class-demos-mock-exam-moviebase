package data.util;

import util.Config;
import util.Crypto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

    private static final String KEY_DB_URL = "db.url";
    private static final String KEY_DB_UID = "db.uid";
    private static final String KEY_DB_PWD = "db.pwd";

    private static String url;
    private static String uid;
    private static String pwd;

    private MySqlConnection() {}

    static {
        url = Config.getInstance().getSetting(KEY_DB_URL);
        uid = Crypto.getInstance().decrypt(Config.getInstance().getSetting(KEY_DB_UID));
        pwd = Crypto.getInstance().decrypt(Config.getInstance().getSetting(KEY_DB_PWD));
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, uid, pwd);
    }

}
