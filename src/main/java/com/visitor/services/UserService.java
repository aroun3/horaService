package com.visitor.services;

import com.visitor.entities.User;
import com.visitor.repositories.UserRepository;
import com.visitor.service_interfaces.UserServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("userservice")
public class UserService implements UserServiceInterface {
    
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll(Sort.by("username").ascending());
    }

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        if (user.getId() == null) {
            return userRepository.save(user);
        }
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User getOneById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.isPresent() ? user.get() : null;
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }


    @Override
    public boolean activateUser(Integer userId, short status) {
        try {
            User user= getOneById(userId);
            user.setStatus(status);
           User u = update(user);
            return true;

        }catch (Exception e){
            e.printStackTrace();
        }

        return false;


    }
}


