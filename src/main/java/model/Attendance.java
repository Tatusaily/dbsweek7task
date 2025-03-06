package model;

import converter.AttendanceStatusConverter;
import jakarta.persistence.*;

@Entity
@Table(name = "attendances")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Convert(converter = AttendanceStatusConverter.class)
    private AttendanceStatus status;
    private String notes;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }



}
