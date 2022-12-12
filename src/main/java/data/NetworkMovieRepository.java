package data;

import domain.Movie;
import messages.MovieResultMessage;
import messages.MovieSearchMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NetworkMovieRepository implements MovieRepository {
    private static final Logger LOGGER = Logger.getLogger(NetworkMovieRepository.class.getName());

    @Override
    public List<Movie> findMoviesByPartOfTitle(String query) {

        try (Socket socket = new Socket("172.21.24.8", 32768);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
            MovieSearchMessage msm = new MovieSearchMessage(query);
            oos.writeObject(msm);
            MovieResultMessage mrm = (MovieResultMessage) ois.readObject();

            return mrm.getResults();
        } catch (IOException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Unable to communicate with server.", ex);
            return null;
        }

    }
}
