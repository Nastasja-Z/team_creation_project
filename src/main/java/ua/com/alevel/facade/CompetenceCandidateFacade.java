package ua.com.alevel.facade;

import ua.com.alevel.persistence.entity.references.CompetenceCandidate;
import ua.com.alevel.web.dto.response.CompetenceCandidateResponseDto;
import ua.com.alevel.web.dto.response.IndicatorProjectResponseDto;

import java.util.List;

public interface CompetenceCandidateFacade extends BaseFacade<CompetenceCandidateResponseDto, CompetenceCandidate> {

    List<CompetenceCandidateResponseDto> findAllByCandidateId(Long id);

    void deleteByCandidateId(Long id);
}
