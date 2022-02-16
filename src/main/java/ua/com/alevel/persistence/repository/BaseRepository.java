package ua.com.alevel.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.BaseEntity;

@Repository
public interface BaseRepository <E extends BaseEntity> extends JpaRepository<E, Long> { }
