package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNonExistsException;
import ua.com.alevel.persistence.entity.Indicator;
import ua.com.alevel.persistence.repository.IndicatorRepository;
import ua.com.alevel.persistence.repository.ProjectRepository;
import ua.com.alevel.service.IndicatorService;

import java.util.Collection;

@Service
public class IndicatorServiceImpl implements IndicatorService {

    private final IndicatorRepository indicatorRepository;
    private final ProjectRepository projectRepository ;

    public IndicatorServiceImpl(IndicatorRepository indicatorRepository, ProjectRepository projectRepository) {
        this.indicatorRepository = indicatorRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public void create(Indicator entity) {
        indicatorRepository.save(entity);
    }

    @Override
    public void update(Indicator entity) {
        checkByExist(entity.getId());
        indicatorRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        checkByExist(id);
        indicatorRepository.deleteById(id);
    }

    @Override
    public Indicator findById(Long id) {
        checkByExist(id);
        return indicatorRepository.findById(id).get();
    }

    @Override
    public Collection<Indicator> findAll() {
        return indicatorRepository.findAll();
    }

    @Override
    public Collection<Indicator> findAllByProjectId(Long projectId) {
        //check if projectId null
        //checkByExist(findById(projectId).getId());
        return indicatorRepository.findByProject(projectRepository.findById(projectId).get());
    }

    private boolean checkByExist(Long id) {
        if (!indicatorRepository.existsById(id)) {
            try {
                throw new EntityNonExistsException("no indicator such this one");
            } catch (EntityNonExistsException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
