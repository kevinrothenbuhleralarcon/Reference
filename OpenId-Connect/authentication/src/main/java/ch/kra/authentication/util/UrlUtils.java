package ch.kra.authentication.util;

import ch.kra.authentication.exception.BadRequestException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.net.URI;
import java.util.Optional;

import static ch.kra.authentication.security.oauth2.CookieAuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;


/*
 * The role of this utils class is to retrieve the url from the cookie and validate it, if not valid return the default url.
 */
public class UrlUtils {
    public static final String DEFAULT_TARGET_URL = "/";

    public static String getTargetUrl(final HttpServletRequest request) {
        Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME).map(Cookie::getValue);

        if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
            throw new BadRequestException("Sorry! We've got an Unauthorized Redirect URI and can't proceed with the authentication");
        }

        return redirectUri.orElse(DEFAULT_TARGET_URL);
    }

    private static boolean isAuthorizedRedirectUri(String uri) {
        URI clientRedirectUri = URI.create(uri);
        URI authorizedURI = URI.create("http://localhost:4200");
        return authorizedURI.getHost().equals(clientRedirectUri.getHost()) && authorizedURI.getPort() == clientRedirectUri.getPort();
    }
}
