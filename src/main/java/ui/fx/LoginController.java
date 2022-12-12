package ui.fx;

import domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import services.MovieService;
import util.MovieException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());

    @FXML
    private Label lblErrorMessage;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private ResourceBundle resources;

    private final MovieService service = new MovieService();

    @FXML
    private void onLogin(ActionEvent e) {
        String login = txtUsername.getText();
        String clearPwd = txtPassword.getText();

        try {
            User user = service.login(login, clearPwd);
            showMovieScreen(user);
        } catch (MovieException ex) {
            showErrorMessage(ex.getMessage());
        }
    }

    @FXML
    private void onRegister(ActionEvent e) {
        String login = txtUsername.getText();
        String clearPwd = txtPassword.getText();

        try {
            User user = service.register(login, clearPwd);
            showMovieScreen(user);
        } catch (MovieException ex) {
            showErrorMessage(ex.getMessage());
        }
    }

    private void showErrorMessage(String message) {
       lblErrorMessage.setText(message);
    }

    private void showMovieScreen(User user) {
        Scene scene = txtUsername.getParent().getScene();

        URL url = getClass().getResource("/fxml/MovieReview.fxml");
        ResourceBundle bundle = ResourceBundle.getBundle("MovieReview", resources.getLocale());
        FXMLLoader loader = new FXMLLoader(url, bundle);

        try {
            Parent root = loader.load();
            MovieReviewController ctlr = loader.getController();
            ctlr.setUsername(user.getUsername());

            scene.setRoot(root);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Unable to open main screen.", ex);
            throw new MovieException("Unable to open application.");
        }
    }

}
