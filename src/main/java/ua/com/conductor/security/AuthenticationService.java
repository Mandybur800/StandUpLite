package ua.com.conductor.security;

import ua.com.conductor.model.User;

public interface AuthenticationService {
    User register(String email, String password);
}
