package ua.com.conductor.service.impl;

import java.util.Optional;
import ua.com.conductor.exception.AuthenticationException;
import ua.com.conductor.lib.Injector;
import ua.com.conductor.lib.Service;
import ua.com.conductor.model.User;
import ua.com.conductor.service.AuthenticationService;
import ua.com.conductor.service.UserService;
import ua.com.conductor.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Injector injector = Injector.getInstance("ua.com.conductor");
    private UserService userService = (UserService)
            injector.getInstance(UserService.class);

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> optionalUser = userService.findByEmail(email);
        User user = optionalUser.get();
        String hashedPassword = HashUtil.hashPassword(password, user.getSalt());
        if (hashedPassword.equals(user.getPassword())) {
            return user;
        }
        throw new AuthenticationException("Incorrect login or password");
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        byte[] salt = HashUtil.getSalt();
        String hashPassword = HashUtil.hashPassword(password, salt);
        user.setSalt(salt);
        user.setPassword(hashPassword);
        userService.add(user);
        return user;
    }
}
