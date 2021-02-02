package ua.com.conductor.service;

import ua.com.conductor.exception.AuthenticationException;
import ua.com.conductor.model.User;

public interface AuthenticationService {
    User login(String email, String password) throws AuthenticationException;

    User register(String email, String password);
}
