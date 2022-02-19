package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.IndicatorProject;
import ua.com.alevel.persistence.repository.IndicatorProjectRepository;
import ua.com.alevel.persistence.repository.IndicatorRepository;
import ua.com.alevel.persistence.repository.ProjectRepository;
import ua.com.alevel.service.IndicatorProjectService;

import java.util.Collection;
import java.util.List;

@Service
public class IndicatorProjectServiceImpl implements IndicatorProjectService {

    private final IndicatorProjectRepository indicatorProjectRepository;
    private final ProjectRepository projectRepository;
    private final IndicatorRepository indicatorRepository;

    public IndicatorProjectServiceImpl(IndicatorProjectRepository indicatorProjectRepository, ProjectRepository projectRepository, IndicatorRepository indicatorRepository) {
        this.indicatorProjectRepository = indicatorProjectRepository;
        this.projectRepository = projectRepository;
        this.indicatorRepository = indicatorRepository;
    }

    @Override
    public void create(IndicatorProject entity) {

    }

    @Override
    public void update(IndicatorProject entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public IndicatorProject findById(Long id) {
        return null;
    }

    @Override
    public Collection<IndicatorProject> findAll() {
        return indicatorProjectRepository.findAll();
    }

    @Override
    public List<IndicatorProject> findAllByProject(Long id) {
        return (List<IndicatorProject>) indicatorProjectRepository.findAllByProject(projectRepository.findById(id).get());
    }

    @Override
    public IndicatorProject findByProjectAndIndicator(Long projectId, Long indicatorId) {
        return indicatorProjectRepository.findByProjectAndIndicator(
                projectRepository.findById(projectId).get(),
                indicatorRepository.findById(indicatorId).get()
        );
    }
}
