package com.example.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.springboot.dao.*;
import com.example.springboot.model.*;

@Service
public class UserDetailsServiceImp implements UserDetailsService {


    private final UserDao userDao;

    @Autowired
    public UserDetailsServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userDao.getUserByFirstName(name);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
