package ua.com.alevel.persistence.entity.references;

import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.Candidate;
import ua.com.alevel.persistence.entity.Competence;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "competences_candidates")
public class CompetenceCandidate extends BaseEntity {

    @ManyToOne(
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST}
    )
    @JoinColumn(name = "competence_id")
    Competence competence;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    Candidate candidate;

    int level;

    public Competence getCompetence() {
        return competence;
    }

    public void setCompetence(Competence competence) {
        this.competence = competence;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CompetenceCandidate that = (CompetenceCandidate) o;
        return level == that.level && Objects.equals(competence, that.competence) && Objects.equals(candidate, that.candidate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), competence, candidate, level);
    }
}
