package com.example.springboot.dao;

import com.example.springboot.model.Role;

import java.util.List;

public interface RoleDao {

    List<Role> getRoles();

    Role getRoleById(Long id);

}
