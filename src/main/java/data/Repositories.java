package data;

import services.MovieService;

public class Repositories {

    private static final MovieRepository MOVIE_REPOSITORY = new NetworkMovieRepository();
    private static final UserRepository USER_REPOSITORY = new MySqlUserRepository();
    private static final ReviewRepository REVIEW_REPOSITORY = new MySqlReviewRepository();

    private Repositories() {}

    public static MovieRepository getMovieRepository() {
        return MOVIE_REPOSITORY;
    }

    public static UserRepository getUserRepository() {
        return USER_REPOSITORY;
    }

    public static ReviewRepository getReviewRepository() {
        return REVIEW_REPOSITORY;
    }
}
