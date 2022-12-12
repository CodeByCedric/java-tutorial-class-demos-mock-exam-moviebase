package data;

public class Repositories {

    private static final MovieRepository MOVIE_REPOSITORY = new NetworkMovieRepository();

    private Repositories() {}

    public static MovieRepository getMovieRepository() {
        return MOVIE_REPOSITORY;
    }

}
