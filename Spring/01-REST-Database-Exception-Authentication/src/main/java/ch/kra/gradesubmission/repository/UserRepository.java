package ch.kra.gradesubmission.repository;

import ch.kra.gradesubmission.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
