package ch.kra.authentication.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.util.SerializationUtils;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

public class CookieUtils {
    public static Optional<Cookie> getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return Optional.of(cookie);
                }
            }
        }

        return Optional.empty();
    }

    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
    }

    public static String encrypt(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return Base64.getUrlEncoder().encodeToString(SerializationUtils.serialize(object));
    }

    public static OAuth2AuthorizationRequest decrypt(Cookie cookie) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
//        return mapper.readValue(Base64.getUrlDecoder().decode(cookie.getValue()), OAuth2AuthorizationRequest.class);
        // Need to find a non deprecated alternative
        return (OAuth2AuthorizationRequest) (SerializationUtils.deserialize(Base64.getUrlDecoder().decode(cookie.getValue())));
    }
}
