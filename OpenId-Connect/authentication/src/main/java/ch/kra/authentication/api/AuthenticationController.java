package ch.kra.authentication.api;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class AuthenticationController {

    private final Environment env;

    @GetMapping("/oauth2-credentials")
    public ResponseEntity<Map<String, String>> getOAuth2Credentials() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("clientId", env.getProperty("spring.security.oauth2.client.registration.google.client-id"));
        credentials.put("issuerUri", env.getProperty("spring.security.oauth2.client.provider.google.issuer-uri"));
        return ResponseEntity.ok(credentials);
    }

    @GetMapping("/test")
    public ResponseEntity test() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
