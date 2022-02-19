package ua.com.alevel.facade;

import ua.com.alevel.persistence.entity.references.IndicatorProject;
import ua.com.alevel.web.dto.response.IndicatorProjectResponseDto;

import java.util.List;

public interface IndicatorProjectFacade extends BaseFacade<IndicatorProjectResponseDto, IndicatorProject>{

    List<IndicatorProjectResponseDto> findAllByProjectId(Long id);

    void deleteByProjectId(Long id);
}
