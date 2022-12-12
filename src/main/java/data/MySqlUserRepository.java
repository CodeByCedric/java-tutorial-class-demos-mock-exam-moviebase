package data;

import data.util.MySqlConnection;
import domain.User;
import util.MovieException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySqlUserRepository implements UserRepository {

    private static final Logger LOGGER = Logger.getLogger(MySqlUserRepository.class.getName());

    private static final String SQL_SELECT_USER = "SELECT * FROM users WHERE username = ?";
    private static final String SQL_INSERT_USER = "INSERT INTO users(username, password) VALUES(?, ?)";

    @Override
    public User getUser(String username) {
        try (Connection con = MySqlConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(SQL_SELECT_USER)) {
            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getString("username"), rs.getString("password"));
                } else {
                    return null;
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Unable to retrieve user from database.", ex);
            throw new MovieException("Unable to get user.");
        }
    }

    @Override
    public void addUser(User user) {
        try (Connection con = MySqlConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(SQL_INSERT_USER)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Unable to add user to database.", ex);
            throw new MovieException("Unable to add user.");
        }
    }
}
