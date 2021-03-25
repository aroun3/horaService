package com.visitor.services;

import com.visitor.entities.Role;
import com.visitor.repositories.RoleRepository;
import com.visitor.service_interfaces.RoleServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("roleService")
public class RoleService implements RoleServiceInterface {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role add(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role update(Role role) {
        if (role.getId() == null) {
            return roleRepository.save(role);
        }
        return roleRepository.saveAndFlush(role);
    }

    @Override
    public Role getOneById(Integer id) {
       return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
