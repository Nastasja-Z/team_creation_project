package ua.com.alevel.persistence.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.com.alevel.persistence.entity.Project;
import ua.com.alevel.persistence.entity.User;

import java.util.Collection;

@Repository
public interface UserRepository extends BaseRepository<User> {

    User findByUsername(String userName);

    boolean existsByUsername(String username);

    Collection<User> findByProjects_Id(Long id);

    /*@Modifying
    @Query("update User u set u.project = project where u.id = :userId")
    void addProject(@Param("project") Project project, @Param("userId") Long userId);*/
}
