package ua.com.alevel.persistence.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "competences")
public class Competence extends BaseEntity {

    @Column(name = "competence_name", nullable = false)
    private String competenceName;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST},
            targetEntity = Candidate.class)
    @JoinTable(name = "competences_candidates",
            inverseJoinColumns = @JoinColumn(name = "candidate_id"),
            joinColumns = @JoinColumn(name = "competence_id"),
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Set<Candidate> candidates;

    private Integer level;

    public String getCompetenceName() {
        return competenceName;
    }

    public void setCompetenceName(String competenceName) {
        this.competenceName = competenceName;
    }

    public Set<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(Set<Candidate> candidates) {
        this.candidates = candidates;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Competence that = (Competence) o;
        return Objects.equals(competenceName, that.competenceName) && Objects.equals(candidates, that.candidates) && Objects.equals(level, that.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), competenceName, candidates, level);
    }
}
