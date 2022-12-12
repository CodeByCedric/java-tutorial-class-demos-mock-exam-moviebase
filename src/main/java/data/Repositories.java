package data;

public class Repositories {

    private static final MovieRepository MOVIE_REPOSITORY = new NetworkMovieRepository();
    private static final UserRepository USER_REPOSITORY = new MySqlUserRepository();
    private Repositories() {}

    public static MovieRepository getMovieRepository() {
        return MOVIE_REPOSITORY;
    }

    public static UserRepository getUserRepository() {
        return USER_REPOSITORY;
    }
}
