package model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "instructors")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String specialization;
    private int experienceYears;

    @Column(name = "created_at", updatable = false)
    private java.sql.Timestamp createdAt;

    @Column(name = "updated_at")
    private java.sql.Timestamp updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new java.sql.Timestamp(System.currentTimeMillis());
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new java.sql.Timestamp(System.currentTimeMillis());
    }

    @PostLoad
    protected void onLoad() {
        System.out.println("Instructor loaded from database");
    }

    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
    private List<TrainingSession> trainingSessions;

    public Instructor() {}

    public Instructor(String name, String specialization, int experienceYears) {
        this.name = name;
        this.specialization = specialization;
        this.experienceYears = experienceYears;
    }

    public boolean getName() {
        return name != null;
    }

    public Object getSpecialization() {
        return specialization;
    }

    // Getters and Setters
}
