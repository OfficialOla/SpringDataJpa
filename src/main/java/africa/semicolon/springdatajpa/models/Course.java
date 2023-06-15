package africa.semicolon.springdatajpa.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(
        name = "Course"
)
@Table(
        name = "course"
)
public class Course {
    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;
    @Column(
            name = "department",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String department;
    @OneToMany(
            mappedBy = "course",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private List<Enrolment> enrolments = new ArrayList<>();
    public Course() {
    }

    public Course(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    public List<Enrolment> getEnrolments(){
        return enrolments;
    }
    public void addEnrolment(Enrolment enrolment){
        if (!enrolments.contains(enrolment)){
            enrolments.add(enrolment);
        }
    }
    public void removeEnrolment(Enrolment enrolment){
        enrolments.remove(enrolment);
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Course{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", department='").append(department).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
