package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.User;

import java.util.Collection;

public interface UserService extends BaseService<User>{

    User findByUsername(String username);

    //remake with response dto, but  in facade already
    Collection<User> findByProjectId(Long projectId);

    //maybe return smth
}
