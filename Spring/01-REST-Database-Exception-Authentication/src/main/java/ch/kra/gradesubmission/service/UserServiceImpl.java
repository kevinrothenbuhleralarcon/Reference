package ch.kra.gradesubmission.service;

import ch.kra.gradesubmission.exception.EntityNotFoundException;
import ch.kra.gradesubmission.model.User;
import ch.kra.gradesubmission.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, User.class));
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
