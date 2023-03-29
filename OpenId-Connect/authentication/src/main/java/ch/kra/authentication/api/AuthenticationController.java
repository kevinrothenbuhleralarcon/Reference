package ch.kra.authentication.api;

import ch.kra.authentication.dto.SignupRequest;
import ch.kra.authentication.exception.UserAlreadyExistException;
import ch.kra.authentication.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final Environment env;
    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<?> home() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/oauth2-credentials")
    public ResponseEntity<Map<String, String>> getOAuth2Credentials() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("clientId", env.getProperty("spring.security.oauth2.client.registration.google.client-id"));
        credentials.put("issuerUri", env.getProperty("spring.security.oauth2.client.provider.google.issuer-uri"));
        return ResponseEntity.ok(credentials);
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        try {
            userService.registerNewUser(signupRequest);
        } catch (UserAlreadyExistException e) {
//            log.error("Exception Ocurred", e);
//            return new ResponseEntity<>(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}
