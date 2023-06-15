package africa.semicolon.springdatajpa.models;

import jakarta.persistence.*;

@Entity(
        name = "StudentIdCard"
)
@Table(
        name = "student_id_card",
        uniqueConstraints ={
                @UniqueConstraint(name = "student_id_card_number_unique", columnNames = "card_number")
        }
)
public class StudentIdCard {
    @Id
    @SequenceGenerator(
            name = "student_id_card_sequence",
            sequenceName = "student_id_card_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_id_card_sequence"

    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;


    @Column(
            name = "card_number",
            nullable = false,
            length = 15
    )
    private String card_number;

    @OneToOne(cascade = CascadeType.ALL,
                fetch = FetchType.EAGER)
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "student_id_fk")
    )
    private Student student;

    public StudentIdCard(String card_number) {
        this.card_number = card_number;
    }

    public StudentIdCard(String card_number, Student student) {
        this.card_number = card_number;
        this.student = student;
    }

    public StudentIdCard() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("StudentIdCard{");
        sb.append("id=").append(id);
        sb.append(", card_number='").append(card_number).append('\'');
        sb.append(", student=").append(student);
        sb.append('}');
        return sb.toString();
    }
}
