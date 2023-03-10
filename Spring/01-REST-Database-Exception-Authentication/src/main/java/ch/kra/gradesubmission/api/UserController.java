package ch.kra.gradesubmission.api;

import ch.kra.gradesubmission.model.User;
import ch.kra.gradesubmission.security.SecurityConfig;
import ch.kra.gradesubmission.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
public class UserController {

    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable final Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @PostMapping(SecurityConfig.REGISTER)
    public ResponseEntity<User> createUser(@Valid @RequestBody final User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }
}
