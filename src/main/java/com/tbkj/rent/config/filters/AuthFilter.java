package com.tbkj.rent.config.filters;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.tbkj.rent.model.entity.User;
import com.tbkj.rent.service.UserService;
import com.tbkj.rent.utils.HttpUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private final FirebaseAuth auth;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            final FirebaseToken idToken = verifyRequest(request);
            Optional<User> optional = userService.getUserByEmail(idToken.getEmail());
            if (optional.isEmpty()) {
                throw new BadCredentialsException("User does not exists");
            }
            setUserToSecurityContext(optional.get(), request);
            filterChain.doFilter(request, response);
        } catch (FirebaseAuthException | BadCredentialsException e) {
            log.warn("Request not Authenticated : {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }

    private FirebaseToken verifyRequest(final HttpServletRequest request)
            throws FirebaseAuthException, ServletException {
        final String token = HttpUtils.parseBearerToken(request);
        if (Objects.isNull(token)) {
            final String message = String.format("%s Token not found in %s Header", HttpUtils.BEARER,
                    HttpUtils.AUTHENTICATION);
            throw new BadCredentialsException(message);
        }
        return auth.verifyIdToken(token);
    }

    private void setUserToSecurityContext(final User user, final HttpServletRequest request) {
        final var authentication = new UsernamePasswordAuthenticationToken(user, Optional.empty(),
                user.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
