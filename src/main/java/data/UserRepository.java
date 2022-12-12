package data;

import domain.User;

public interface UserRepository {

    User getUser(String username);
    void addUser(User user);

}
