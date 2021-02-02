package ua.com.conductor.service.impl;

import java.util.Optional;
import ua.com.conductor.dao.UserDao;
import ua.com.conductor.lib.Inject;
import ua.com.conductor.lib.Service;
import ua.com.conductor.model.User;
import ua.com.conductor.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User add(User user) {
        return userDao.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
