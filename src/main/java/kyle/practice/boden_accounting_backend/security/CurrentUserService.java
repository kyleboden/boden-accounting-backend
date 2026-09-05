package kyle.practice.boden_accounting_backend.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService {

    public String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("Authenticated user is required");
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof Jwt jwt
                && "authenticated".equals(jwt.getClaimAsString("role"))
                && jwt.getSubject() != null
                && !jwt.getSubject().isBlank()) {
            return jwt.getSubject();
        }

        throw new AccessDeniedException("Authenticated user is required");
    }
}
