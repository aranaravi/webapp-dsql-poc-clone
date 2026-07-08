package jsp.springboot.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.Map;

@RestController
public class UserController {

    @GetMapping("/")
    public Map<String, Object> me(
            @AuthenticationPrincipal OidcUser user) {

        return Map.of(
                "username", user.getPreferredUsername(),
                "name", user.getFullName(),
                "email", user.getEmail()
        );
    }

    @GetMapping("/check-session")
    public String check(Authentication authentication) {
        if (authentication != null) {
            return "User Logged In";
        }
        return "Not Logged In";
    }

    @GetMapping("/logout-user")
    public String logout(HttpServletRequest request)
            throws ServletException {
        request.logout();
        return "Logged out";
    }
}