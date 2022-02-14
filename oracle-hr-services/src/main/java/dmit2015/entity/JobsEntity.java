package dmit2015.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "JOBS", schema = "HR", catalog = "")
public class JobsEntity {

    @Id
    @Column(name = "JOB_ID")
    private String jobId;
    @Basic
    @Column(name = "JOB_TITLE")
    private String jobTitle;
    @Basic
    @Column(name = "MIN_SALARY")
    private Integer minSalary;
    @Basic
    @Column(name = "MAX_SALARY")
    private Integer maxSalary;
    @OneToMany(mappedBy = "jobsByJobId")
    private Collection<EmployeesEntity> employeesByJobId;
    @OneToMany(mappedBy = "jobsByJobId")
    private Collection<JobHistoryEntity> jobHistoriesByJobId;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Integer getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Integer minSalary) {
        this.minSalary = minSalary;
    }

    public Integer getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Integer maxSalary) {
        this.maxSalary = maxSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobsEntity that = (JobsEntity) o;

        if (jobId != null ? !jobId.equals(that.jobId) : that.jobId != null) return false;
        if (jobTitle != null ? !jobTitle.equals(that.jobTitle) : that.jobTitle != null) return false;
        if (minSalary != null ? !minSalary.equals(that.minSalary) : that.minSalary != null) return false;
        if (maxSalary != null ? !maxSalary.equals(that.maxSalary) : that.maxSalary != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = jobId != null ? jobId.hashCode() : 0;
        result = 31 * result + (jobTitle != null ? jobTitle.hashCode() : 0);
        result = 31 * result + (minSalary != null ? minSalary.hashCode() : 0);
        result = 31 * result + (maxSalary != null ? maxSalary.hashCode() : 0);
        return result;
    }

    public Collection<EmployeesEntity> getEmployeesByJobId() {
        return employeesByJobId;
    }

    public void setEmployeesByJobId(Collection<EmployeesEntity> employeesByJobId) {
        this.employeesByJobId = employeesByJobId;
    }

    public Collection<JobHistoryEntity> getJobHistoriesByJobId() {
        return jobHistoriesByJobId;
    }

    public void setJobHistoriesByJobId(Collection<JobHistoryEntity> jobHistoriesByJobId) {
        this.jobHistoriesByJobId = jobHistoriesByJobId;
    }
}
