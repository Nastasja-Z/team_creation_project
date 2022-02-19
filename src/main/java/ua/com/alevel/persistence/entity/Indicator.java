package ua.com.alevel.persistence.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "indicators")
public class Indicator extends BaseEntity {

    @Column(name = "indicator_name")
    private String indicatorName;

    @OneToMany(mappedBy = "indicator")
    private Set<IndicatorProject> projects;


    public String getIndicatorName() {
        return indicatorName;
    }

    public void setIndicatorName(String indicatorName) {
        this.indicatorName = indicatorName;
    }

    public Set<IndicatorProject> getProjects() {
        return projects;
    }

    public void setProjects(Set<IndicatorProject> projects) {
        this.projects = projects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Indicator indicator = (Indicator) o;
        return Objects.equals(indicatorName, indicator.indicatorName) && Objects.equals(projects, indicator.projects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), indicatorName, projects);
    }




       /*public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }*/


    /*@ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;*/

 /*   @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST},
            targetEntity = Candidate.class)
    @JoinTable(name = "indicators_projects",
            inverseJoinColumns = @JoinColumn(name = "project_id"),
            joinColumns = @JoinColumn(name = "indicator_id"),
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Set<Project> projects;*/
    //private Integer level;
}
