package ua.com.alevel.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.EntityNonExistsException;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.persistence.repository.ProjectRepository;
import ua.com.alevel.persistence.repository.UserRepository;
import ua.com.alevel.service.UserService;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
//    private final ProjectRepository projectRepository;
    private final BCryptPasswordEncoder encoder;


    public UserServiceImpl(UserRepository userRepository, /*ProjectRepository projectRepository,*/ BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        //this.projectRepository = projectRepository;
        this.encoder = encoder;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(User entity) {
        entity.setPassword(encoder.encode(entity.getPassword()));
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

    @Override
    public Collection<User> findByProjectId(Long projectId) {
        return userRepository.findByProjects_Id(projectId);
    }

    private boolean checkByExist(Long id) {
        if (!userRepository.existsById(id)) {
            try {
                throw new EntityNonExistsException("no user such this one");
            } catch (EntityNonExistsException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
