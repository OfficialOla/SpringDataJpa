package africa.semicolon.springdatajpa.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "Enrolment")
@Table(name = "enrolment")
public class Enrolment {
    @EmbeddedId
    private EnrolmentId id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id",   foreignKey = @ForeignKey(name = "student_enrolment_id_fk")   )
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(
            name = "course_id",
            foreignKey = @ForeignKey(name = "course_enrolment_id_fk")
    )
    private Course course;
    @Column(
            name = "created_at",
            nullable = false,
            columnDefinition = "DateTime"
    )
    private LocalDateTime createAt;


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Enrolment{");
        sb.append("id=").append(id);
        sb.append(", student=").append(student);
        sb.append(", course=").append(course);
        sb.append('}');
        return sb.toString();
    }

    public Enrolment(EnrolmentId id, Student student, Course course, LocalDateTime createAt ) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.createAt = createAt;
    }

    public Enrolment(Student student, Course course, LocalDateTime createAt) {
        this.student = student;
        this.course = course;
        this.createAt = createAt;
    }

    public Enrolment() {
    }

    public EnrolmentId getId() {
        return id;
    }

    public void setId(EnrolmentId id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}
