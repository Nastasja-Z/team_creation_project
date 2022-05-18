package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNonExistsException;
import ua.com.alevel.persistence.entity.Project;
import ua.com.alevel.persistence.repository.ProjectRepository;
import ua.com.alevel.service.ProjectService;

import java.util.Collection;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void create(Project entity) {
        System.out.println("entity.getStartOfProject() = " + entity.getStartOfProject());
        projectRepository.save(entity);
    }

    @Override
    public void update(Project entity) {
        checkByExist(entity.getId());
        projectRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        checkByExist(id);
        projectRepository.deleteById(id);
    }

    @Override
    public Project findById(Long id) {
        checkByExist(id);
        return projectRepository.findById(id).get();
    }

    @Override
    public Collection<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public void setOfTheWillingnessToCreation(Long projectId) {
        //add check on
        projectRepository.setOfReadyToCreateTheTeam(projectId);
    }

    private boolean checkByExist(Long id) {
        if (!projectRepository.existsById(id)) {
            try {
                throw new EntityNonExistsException("no project such this one");
            } catch (EntityNonExistsException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
