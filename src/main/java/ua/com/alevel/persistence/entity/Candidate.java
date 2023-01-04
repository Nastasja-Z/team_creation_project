package ua.com.alevel.persistence.entity;

import ua.com.alevel.persistence.entity.references.CompetenceCandidate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "candidates")
public class Candidate extends BaseEntity {

    private String nameOfCandidate;
    private String surnameOfCandidate;
    private BigDecimal salaryProHour;
    private Integer employment; // ограничение часов в сутках или ??
    //add date of birth

    @OneToMany(mappedBy = "candidate",
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST}
    )
    private Set<CompetenceCandidate> competences;

    @ManyToMany(mappedBy = "candidates")
    private List<Project> projects=new ArrayList<>();

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public String getNameOfCandidate() {
        return nameOfCandidate;
    }

    public void setNameOfCandidate(String nameOfCandidate) {
        this.nameOfCandidate = nameOfCandidate;
    }

    public String getSurnameOfCandidate() {
        return surnameOfCandidate;
    }

    public void setSurnameOfCandidate(String surnameOfCandidate) {
        this.surnameOfCandidate = surnameOfCandidate;
    }

    public BigDecimal getSalaryProHour() {
        return salaryProHour;
    }

    public void setSalaryProHour(BigDecimal salaryProHour) {
        this.salaryProHour = salaryProHour;
    }

    public Integer getEmployment() {
        return employment;
    }

    public void setEmployment(Integer employment) {
        this.employment = employment;
    }

    public Set<CompetenceCandidate> getCompetences() {
        return competences;
    }

    public void setCompetences(Set<CompetenceCandidate> competences) {
        this.competences = competences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Candidate candidate = (Candidate) o;
        return Objects.equals(nameOfCandidate, candidate.nameOfCandidate) && Objects.equals(surnameOfCandidate, candidate.surnameOfCandidate) && Objects.equals(salaryProHour, candidate.salaryProHour) && Objects.equals(employment, candidate.employment) && Objects.equals(competences, candidate.competences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nameOfCandidate, surnameOfCandidate, salaryProHour, employment, competences);
    }
}
