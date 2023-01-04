package ua.com.alevel.service.impl;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.SetUtils;
import ua.com.alevel.exception.EntityNonExistsException;
import ua.com.alevel.persistence.entity.Role;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.persistence.repository.RoleRepository;
import ua.com.alevel.persistence.repository.UserRepository;
import ua.com.alevel.service.UserService;

import java.util.Collection;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    //    private final ProjectRepository projectRepository;
//    private final BCryptPasswordEncoder encoder;
    private final RoleRepository roleRepository;


    public UserServiceImpl(UserRepository userRepository, /*ProjectRepository projectRepository,*/ /*BCryptPasswordEncoder encoder, */RoleRepository roleRepository) {
        this.userRepository = userRepository;
        //this.projectRepository = projectRepository;
//        this.encoder = encoder;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(User entity) {
        /*Set<Role> roles = SetUtils.singletonSet(roleRepository.findByName(USER_ROLE));
        entity.setPassword(encoder.encode(entity.getPassword()));
        if (CollectionUtils.isEmpty(entity.getRoles())) {
            entity.setRoles(roles);
        }
        return userRepository.save(useer);*/
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
