package africa.semicolon.springdatajpa.repositories;

import africa.semicolon.springdatajpa.models.StudentIdCard;
import org.springframework.data.repository.CrudRepository;

public interface StudentIdCardRepository extends CrudRepository<StudentIdCard, Long> {
}
