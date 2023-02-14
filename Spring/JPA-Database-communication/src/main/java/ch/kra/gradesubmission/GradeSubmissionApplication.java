package ch.kra.gradesubmission;

import ch.kra.gradesubmission.entity.Student;
import ch.kra.gradesubmission.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class GradeSubmissionApplication implements CommandLineRunner {

    @Autowired
    StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(GradeSubmissionApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Student> students = List.of(
                new Student("Harry Potter", LocalDate.parse(("1980-07-31"))),
                new Student("Ron Weasley", LocalDate.parse(("1980-03-01"))),
                new Student("Hermione Granger", LocalDate.parse(("1979-09-19"))),
                new Student("Neville Longbottom", LocalDate.parse(("1980-07-30")))
        );

        studentRepository.saveAll(students);
    }
}
