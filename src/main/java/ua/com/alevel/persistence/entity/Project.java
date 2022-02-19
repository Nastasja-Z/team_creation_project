package ua.com.alevel.persistence.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project extends BaseEntity{

    @Column(name = "project_name")
    private String nameOfProject;

    /*@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST},
            targetEntity = Candidate.class)
    @JoinTable(name = "indicators_projects",
            inverseJoinColumns = @JoinColumn(name = "indicator_id"),
            joinColumns = @JoinColumn(name = "project_id"),
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))*/

    @OneToMany(mappedBy = "project")
    private Set<IndicatorProject> indicators;

    @Column(nullable = false)
    private BigDecimal budget;

    @Column(name = "start_project")
    private Date startOfProject;

    /*@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST},
            targetEntity = Candidate.class)
    @JoinTable(name = "users_projects",
            inverseJoinColumns = @JoinColumn(name = "user_id"),
            joinColumns = @JoinColumn(name = "project_id"),
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Set<User> users;*/

    @Column(name = "end_project") // nullable??
    private Date endOfProject;    // validate!!!

    public String getNameOfProject() {
        return nameOfProject;
    }

    public void setNameOfProject(String nameOfProject) {
        this.nameOfProject = nameOfProject;
    }

    public Set<IndicatorProject> getIndicators() {
        return indicators;
    }

    public void setIndicators(Set<IndicatorProject> indicators) {
        this.indicators = indicators;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public Date getStartOfProject() {
        return startOfProject;
    }

    public void setStartOfProject(Date startOfProject) {
        this.startOfProject = startOfProject;
    }

/*    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }*/

    public Date getEndOfProject() {
        return endOfProject;
    }

    public void setEndOfProject(Date endOfProject) {
        this.endOfProject = endOfProject;
    }

   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Project project = (Project) o;
        return Objects.equals(nameOfProject, project.nameOfProject) && Objects.equals(indicators, project.indicators) && Objects.equals(budget, project.budget) && Objects.equals(startOfProject, project.startOfProject) && Objects.equals(users, project.users) && Objects.equals(endOfProject, project.endOfProject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nameOfProject, indicators, budget, startOfProject, users, endOfProject);
    }*/
}
