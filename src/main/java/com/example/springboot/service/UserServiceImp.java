package com.example.springboot.service;

import com.example.springboot.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.springboot.dao.UserDao;
import com.example.springboot.model.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImp implements UserService {

   private final UserDao userDao;

   @Autowired
   public UserServiceImp(UserDao userDao, RoleDao roleDao) {
      this.userDao = userDao;
   }

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }


   @Transactional
   @Override
   public void remove(Long id) {
      userDao.remove(id);
   }

   @Transactional
   @Override
   public void update(User user) {
      Set<Role> roles = new HashSet<>();
      User oldUser = userDao.getUserById(user.getId());


      user.setPassword(oldUser.getPassword());
      user.setRoles(roles);
      userDao.update(user);
   }

   @Transactional(readOnly = true)
   @Override
   public User getUserById(Long id) {
      return userDao.getUserById(id);
   }

   @Transactional(readOnly = true)
   @Override
   public User getUserByFirstName(String name) {
        return userDao.getUserByFirstName(name);
    }

   @Transactional(readOnly = true)
   @Override
   public boolean checkUserById(Long id) {
      return userDao.checkUserById(id);
   }
}
