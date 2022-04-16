package com.toyvalley.services;

import com.toyvalley.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final List<User> userList;

    public UserService() {
        userList = new ArrayList<User>();
    }

    public List<User> getUsers() {
        return userList;
    }

    public User getUser(long id) {
        for (User userInstance : userList) {
            if (userInstance.getId() == id) {
                return userInstance;
            }
        }
        throw new RuntimeException("User with id " + id + " is not found");
    }

    public User createUser(User model) {
        long id = userList.size() + 1;
        model.setId(id);
        userList.add(model);
        return model;
    }

    public User updateUser(long id, User user) {
        for (User userInstance : userList) {
            if (userInstance.getId() == id) {
                userInstance.update(user);
                return userInstance;
            }
        }
        throw new RuntimeException("User with id " + id + " is not found");
    }

    public boolean deleteUser(long id) {
        for (User userInstance : userList) {
            if (userInstance.getId() == id) {
                //userList.remove(userInstance);
                return userList.remove(userInstance);
            }
        }
        throw new RuntimeException("User with id " + id + " is not found");
    }
}
