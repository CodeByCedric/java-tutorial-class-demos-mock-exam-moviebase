package services;

import data.Repositories;
import domain.Movie;

import java.util.List;

public class MovieService {

    public List<Movie> findMoviesByPartOfTitle(String query) {
        return Repositories.getMovieRepository().findMoviesByPartOfTitle(query);
    }

}
