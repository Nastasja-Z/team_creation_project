package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.facade.IndicatorProjectFacade;
import ua.com.alevel.persistence.entity.references.IndicatorProject;
import ua.com.alevel.service.IndicatorProjectService;
import ua.com.alevel.service.IndicatorService;
import ua.com.alevel.service.ProjectService;
import ua.com.alevel.web.dto.response.IndicatorProjectResponseDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class IndicatorProjectFacadeImpl implements IndicatorProjectFacade {

    private final IndicatorService indicatorService;
    private final ProjectService projectService;
    private final IndicatorProjectService indicatorProjectService ;

    public IndicatorProjectFacadeImpl(IndicatorService indicatorService, ProjectService projectService, IndicatorProjectService indicatorProjectService) {
        this.indicatorService = indicatorService;
        this.projectService = projectService;
        this.indicatorProjectService = indicatorProjectService;
    }

    @Override
    public void create(IndicatorProject indicatorProject) {

    }

    @Override
    public void update(IndicatorProject indicatorProject, Long id) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public IndicatorProjectResponseDto findById(Long id) {
        return null;
    }

    @Override
    public List<IndicatorProjectResponseDto> findAllByProjectId(Long id) {
        List<IndicatorProjectResponseDto> dtos = new ArrayList<>();
        indicatorService.findAllByProjectId(id).forEach(x->{
            dtos.add(new IndicatorProjectResponseDto(x,
                    indicatorProjectService
                            .findByProjectAndIndicator(projectService
                                    .findById(id)
                                    .getId(),x.getId())));
        });
        return dtos;
    }

    @Override
    public void deleteByProjectId(Long id) {
        indicatorProjectService.deleteAllByProject(id);
    }
}
