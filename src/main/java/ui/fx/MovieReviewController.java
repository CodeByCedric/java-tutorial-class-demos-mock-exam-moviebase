package ui.fx;

import domain.Movie;
import domain.Review;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.MovieService;

import java.util.List;

public class MovieReviewController {

    private final MovieService service = new MovieService();

    @FXML
    private Spinner spnScore;

    @FXML
    private ImageView imgCover;

    @FXML
    private Label lblTitle;

    @FXML
    private TextField txtReviewText;

    @FXML
    private ListView lstResults;

    @FXML
    private TextField txtSearch;

    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    @FXML
    private void onSearch(ActionEvent e) {
        String query = txtSearch.getText();
        List<Movie> movies = service.findMoviesByPartOfTitle(query);
        fillInMovies(movies);
    }

    private void fillInMovies(List<Movie> movies) {
        lstResults.setItems(FXCollections.observableList(movies));
    }

    @FXML
    public void onDisplayMovie(ActionEvent e) {
        Movie movie = (Movie) lstResults.getSelectionModel().getSelectedItem();

        Image img = new Image(movie.getCoverUrl());
        imgCover.setImage(img);
        lblTitle.setText(movie.getTitle());
    }

    @FXML
    public void onAddReview(ActionEvent e) {
        Movie movie = (Movie) lstResults.getSelectionModel().getSelectedItem();

        Review review = new Review(username,
                movie, txtReviewText.getText(),
                (Integer) spnScore.getValue());

        service.addReview(review);

        Alert al = new Alert(Alert.AlertType.CONFIRMATION,
                "Review has been added. Thank you!");
        al.showAndWait();
    }
}
