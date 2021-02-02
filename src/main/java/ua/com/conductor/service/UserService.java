package ua.com.conductor.service;

import java.util.Optional;
import ua.com.conductor.model.User;

public interface UserService {
    User add(User user);
        
    Optional<User> findByEmail(String email);
}
