package africa.semicolon.springdatajpa;

import africa.semicolon.springdatajpa.models.*;
import africa.semicolon.springdatajpa.repositories.StudentRepository;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@Slf4j
public class SpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
		return args -> {
			Faker faker = new Faker();
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@officialyo.com", firstName, lastName);
			int age = faker.number().numberBetween(17,40);
			Student student = new Student(
					firstName,
					lastName,
					age,
					email
			);
			student.addBook(
					new Book("Clean code", LocalDateTime.now())
			);student.addBook(
					new Book("Effective guide to software engineering", LocalDateTime.now().minusYears(2))
			);student.addBook(
					new Book("Test Driven Development guide for beginner", LocalDateTime.now().minusDays(6))
			);

			StudentIdCard studentIdCard = new StudentIdCard("12345", student);
			student.setStudentIdCard(studentIdCard);
			student.addEnrolment( new Enrolment(
					new EnrolmentId(1L, 1L),
					student,
					new Course("Computer Science", "IT"), LocalDateTime.now()));
			student.addEnrolment( new Enrolment(
					new EnrolmentId(1L, 2L),
					student,
					new Course("Industrial Design", "IT"), LocalDateTime.now().minusDays(18)));

			studentRepository.save(student);
			studentRepository.findById(1L)
					.ifPresent(student1 -> {
						System.out.println("Fetching lazy...");
						List<Book> books = student.getBooks();
						books.forEach(book -> {
							System.out.println(student1.getFirstName() + " borrowed " + book.getBookName());
						});
					});
//			studentIdCardRepository.findById(1L)
//					.ifPresent(System.out::println);

//			studentRepository.deleteById(1L);
//			generateRandomStudents(studentRepository);
//			sortStudents(studentRepository);
//			PageRequest pageRequest = PageRequest.of(0, 5,  Sort.by("firstName").ascending());
//			System.out.println(studentRepository.findAll(pageRequest));


//			Student Yinka = new Student(
//					"Yinka",
//					"Ola",
//					20,
//					"email@email.com"
//			);
//			Student Josh = new Student(
//					"Josh",
//					"Kuse",
//					21,
//					"josh@email.com"
//			);
//			Student Yinka2 = new Student(
//					"Yinka",
//					"Omotinugbon",
//					25,
//					"Tinuade@email.com"
//			);
//			log.info("Adding Yinka, Josh and Yinka2");
//			studentRepository.saveAll(List.of(Yinka, Josh, Yinka2));
//			studentRepository
//					.findStudentByEmail("josh@email.com")
//					.ifPresentOrElse(System.out::println,
//							()-> System.out.println("Student with email josh@email.com does not exist"));
//			studentRepository
//					.findStudentByEmail("amotekun@amotekun.com")
//					.ifPresentOrElse(System.out::println,
//							()-> System.out.println("Student with email amotekun@amotekun.com does not exist"));
//			studentRepository.findStudentsByFirstNameEqualsAndAgeIsGreaterThanEqual(
//					"Yinka",
//					18
//			).forEach(System.out::println);
//
//			System.out.println(studentRepository.deleteStudentBy(1L));
//			log.info("Number of students");
//			System.out.println(studentRepository.count());
//
//			studentRepository
//					.findById(2L)
//					.ifPresentOrElse(System.out::println, () -> {System.out.println("student with ID 2 not found");
//			});
//			studentRepository
//					.findById(3L)
//					.ifPresentOrElse(System.out::println, () -> {System.out.println("student with ID 3 not found");
//			});
//			log.info("Select all students");
//			List<Student> students = studentRepository.findAll();
//			students.forEach(System.out::println);
//
//			log.info("Delete student with ID 1");
//			studentRepository.deleteById(1L);
//			System.out.println(studentRepository.count());
		};
	}

	private static void sortStudents(StudentRepository studentRepository) {
		Sort sortedStudents = Sort.by("firstName").ascending()
				.and(Sort.by("age").descending());
		studentRepository.findAll(sortedStudents)
				.forEach(student -> System.out.println(student.getFirstName() + " "+ student.getAge()));
	}

	private static void generateRandomStudents(StudentRepository studentRepository) {
		Faker faker = new Faker();
		for (int i = 0; i <20 ; i++) {
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@officialyo.com", firstName, lastName);
			int age = faker.number().numberBetween(17,40);
			Student student = new Student(
					firstName,
					lastName,
					age,
					email
			);
			studentRepository.save(student);
		}
	}
}
