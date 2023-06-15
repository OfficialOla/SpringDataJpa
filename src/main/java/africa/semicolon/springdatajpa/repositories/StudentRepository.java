package africa.semicolon.springdatajpa.repositories;

import africa.semicolon.springdatajpa.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE s.email = ?1" )
    Optional<Student> findStudentByEmail(String email);
    @Query("SELECT s FROM Student s WHERE s.firstName =?1 AND s.age >=?2")
    List<Student> findStudentsByFirstNameEqualsAndAgeIsGreaterThanEqual(String firstname, int age);
    @Transactional
    @Modifying
    @Query("DELETE FROM Student u WHERE u.id =?1")
    int deleteStudentBy(Long id);
}
