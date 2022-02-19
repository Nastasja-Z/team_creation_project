package ua.com.alevel.web.dto.response;

import ua.com.alevel.persistence.entity.Competence;
import ua.com.alevel.persistence.entity.references.CompetenceCandidate;

public class CompetenceCandidateResponseDto extends ResponseDto {

    private String competenceName;
    private int level;

    public CompetenceCandidateResponseDto(
            Competence competence,
            CompetenceCandidate competenceCandidate
    ) {
        super(competenceCandidate.getCandidate().getId());
        this.competenceName = competence.getCompetenceName();
        this.level = competenceCandidate.getLevel();
    }

    public String getCompetenceName() {
        return competenceName;
    }

    public void setCompetenceName(String competenceName) {
        this.competenceName = competenceName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
