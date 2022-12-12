package data;

import data.util.MySqlConnection;
import domain.Review;
import util.MovieException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySqlReviewRepository implements ReviewRepository {

    private static final Logger LOGGER = Logger.getLogger(MySqlReviewRepository.class.getName());

    private static final String SQL_INSERT_REVIEW =
            "INSERT INTO reviews(username, movie_id, review, score)" +
                    "VALUES(?, ?, ?, ?)";


    @Override
    public void addReview(Review review) {
        try (Connection con = MySqlConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(SQL_INSERT_REVIEW)) {
            stmt.setString(1, review.getUsername());
            stmt.setInt(2, review.getMovie().getId());
            stmt.setString(3, review.getReview());
            stmt.setInt(4, review.getScore());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Unable to add review.", ex);
            throw new MovieException("Unable to add review.");
        }
    }
}
