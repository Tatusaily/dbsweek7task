package model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "progress_reports")
public class ProgressReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp reportDate;
    private String achievements;
    private String areasOfImprovement;

    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Version
    private int version;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;


    public ProgressReport() {}

    public ProgressReport(Timestamp reportDate, String achievements, String areasOfImprovement, Student student) {
        this.reportDate = reportDate;
        this.achievements = achievements;
        this.areasOfImprovement = areasOfImprovement;
        this.student = student;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }

    public void setReportDate(Timestamp reportDate) {
        this.reportDate = reportDate;
    }

    public Timestamp getReportDate() {
        return reportDate;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }

    public String getAchievements() {
        return achievements;
    }

    public void setAreasOfImprovement(String areasOfImprovement) {
        this.areasOfImprovement = areasOfImprovement;
    }

    public String getAreasOfImprovement() {
        return areasOfImprovement;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
