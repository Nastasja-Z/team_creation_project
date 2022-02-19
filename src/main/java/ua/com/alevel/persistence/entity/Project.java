package ua.com.alevel.persistence.entity;

import org.springframework.format.annotation.DateTimeFormat;
import ua.com.alevel.persistence.entity.references.IndicatorProject;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project extends BaseEntity{

    @Column(name = "project_name")
    private String nameOfProject;

    @OneToMany(mappedBy = "project")
    private Set<IndicatorProject> indicators;

    @Column(nullable = false)
    private BigDecimal budget;

    @Column(name = "start_project")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startOfProject;

    @Column(name = "end_project") // nullable??
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endOfProject;    // validate!!!

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST},
            targetEntity = User.class)
    @JoinTable(name = "users_projects",
            inverseJoinColumns = @JoinColumn(name = "user_id"),
            joinColumns = @JoinColumn(name = "project_id"),
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Set<User> users;

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

    public Date getEndOfProject() {
        return endOfProject;
    }

    public void setEndOfProject(Date endOfProject) {
        this.endOfProject = endOfProject;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Project project = (Project) o;
        return Objects.equals(nameOfProject, project.nameOfProject) && Objects.equals(indicators, project.indicators) && Objects.equals(budget, project.budget) && Objects.equals(startOfProject, project.startOfProject) && Objects.equals(endOfProject, project.endOfProject) && Objects.equals(users, project.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nameOfProject, indicators, budget, startOfProject, endOfProject, users);
    }
}
