package com.example.springboot.service;
import com.example.springboot.model.User;



import java.util.List;

public interface UserService {

    void add(User user);

    List<User> listUsers();

    void remove(Long id);

    void update(User user);

    User getUserById(Long id);

    User getUserByFirstName(String firstName);

    boolean checkUserById(Long id);
}
