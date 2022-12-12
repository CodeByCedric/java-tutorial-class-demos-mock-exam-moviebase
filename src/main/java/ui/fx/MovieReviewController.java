package ui.fx;

import domain.Movie;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import services.MovieService;

import java.util.List;

public class MovieReviewController {

    private final MovieService service = new MovieService();

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
    }

    @FXML
    public void onAddReview(ActionEvent e) {

    }
}
