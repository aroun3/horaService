package com.visitor.service;

import com.visitor.entities.User;

public interface UserService extends BaseService<User> {
    public boolean activateUser(Integer userId, short status);
}
