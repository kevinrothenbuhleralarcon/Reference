package ch.kra.gradesubmission.service;

import ch.kra.gradesubmission.model.User;

public interface UserService {
    User getUser(Long id);
    User getUser(String username);
    User saveUser(User user);
}
