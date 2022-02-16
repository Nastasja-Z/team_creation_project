package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNonExistsException;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.persistence.repository.UserRepository;
import ua.com.alevel.service.UserService;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository ;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(User entity) {
        //valid date
        userRepository.save(entity);

    }

    @Override
    public void update(User entity) {
        checkByExist(entity.getId());
        userRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        checkByExist(id);
        userRepository.deleteById(id);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public Collection<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        //check if exists
        return userRepository.findByUsername(username);
    }

    private boolean checkByExist(Long id)  {
        if(!userRepository.existsById(id)){
            try {
                throw new EntityNonExistsException("no user such this one");
            } catch (EntityNonExistsException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
