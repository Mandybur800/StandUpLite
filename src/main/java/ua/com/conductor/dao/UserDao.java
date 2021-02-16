package ua.com.conductor.dao;

import java.util.Optional;
import ua.com.conductor.model.User;

public interface UserDao {
    User add(User user);

    Optional<User> findByEmail(String email);

    Optional<User> get(Long id);
}
