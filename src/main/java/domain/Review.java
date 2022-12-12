package domain;

import java.util.Objects;

public class Review {

    private int id;
    private String username;
    private Movie movie;
    private String review;
    private int score;

    public Review(String username, Movie movie, String review, int score) {
        this.username = username;
        this.movie = movie;
        this.review = review;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id == review.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getReview() {
        return review;
    }

    public int getScore() {
        return score;
    }
}
