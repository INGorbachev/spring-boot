package com.example.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springboot.dao.*;
import com.example.springboot.model.*;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {

    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }


    @Override
    public List<Role> getRoles() {
        return roleDao.getRoles();
    }
}
