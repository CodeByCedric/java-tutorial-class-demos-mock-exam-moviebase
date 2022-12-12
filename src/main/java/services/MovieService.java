package services;

import data.Repositories;
import domain.Movie;
import domain.User;
import util.Crypto;
import util.MovieException;

import java.util.List;

public class MovieService {

    public List<Movie> findMoviesByPartOfTitle(String query) {
        return Repositories.getMovieRepository().findMoviesByPartOfTitle(query);
    }

    public User login(String username, String password) {
        User user = Repositories.getUserRepository().getUser(username);

        if (user != null && (Crypto.getInstance().compare(password, user.getPassword()))) {
            return user;
        } else {
            throw new MovieException("Invalid username and/or password.");
        }
    }

    public User register(String username, String password) {
        try {
            User user = new User(username, Crypto.getInstance().hash(password));
            Repositories.getUserRepository().addUser(user);
            return user;
        } catch (MovieException ex) {
            throw new MovieException("Unable to add user. Please choose a unique username.");
        }
    }

}
