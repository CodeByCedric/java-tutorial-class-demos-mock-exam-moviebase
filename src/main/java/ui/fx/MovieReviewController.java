package ui.fx;

import domain.Movie;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.MovieService;

import java.util.List;

public class MovieReviewController {

    private final MovieService service = new MovieService();

    @FXML
    public ImageView imgCover;

    @FXML
    public Label lblTitle;

    @FXML
    private ListView lstResults;

    @FXML
    private TextField txtSearch;

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

    }
}
