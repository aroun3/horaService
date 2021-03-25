package com.visitor.service_interfaces;

import com.visitor.entities.User;

public interface UserServiceInterface extends BaseServiceInterface<User> {
    public boolean activateUser(Integer userId, short status);
}
