package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;

import ua.com.alevel.persistence.entity.Role;

@Repository
public interface RoleRepository extends BaseRepository<Role> {

    Role findByName(String name);
}
