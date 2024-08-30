package com.example.QrOrder.filter;


import com.example.QrOrder.configuarations.JwtTokenUtil;
import org.springframework.data.util.Pair;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    // @Value("${api.prefix}")
    //private String apiPrefix;
    // private final UserServiceImpl userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        try {
            if (isByPassToken(request)) {
                filterChain.doFilter(request, response);
                return;
            }
            final String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                return;
            }
            final String token = authHeader.substring(7); // Bearer loai bo 7 ky tu dau tien di
            final String gmail = jwtTokenUtil.extractPhoneNumber(token);
            if (gmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(gmail);
                if (jwtTokenUtil.validateToken(token, userDetails)) // nếu validateToken thành công thì là còn hạn
                {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            filterChain.doFilter(request, response); //enable bypass
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }
    }

    private final PathMatcher pathMatcher = new AntPathMatcher(); // Khởi tạo PathMatcher

    private boolean isByPassToken(@NonNull HttpServletRequest request) {
        final List<Pair<String, String>> bypassTokens = Arrays.asList(
                Pair.of(String.format("/%s/user/login", "qr_order/v1" /*apiPrefix*/), "POST"),
                Pair.of(String.format("/%s/api/menu/**", "qr_order/v1" /*apiPrefix*/), "GET"),
                Pair.of(String.format("/%s/api/qr-code/tables/**", "qr_order/v1" /*apiPrefix*/), "GET"),
                Pair.of(String.format("/%s/api/orders", "qr_order/v1" /*apiPrefix*/), "POST"),
                Pair.of(String.format("/%s/api/orders/**", "qr_order/v1" /*apiPrefix*/), "PUT"),
                Pair.of(String.format("/%s/api/orders/**", "qr_order/v1" /*apiPrefix*/), "GET"),
                Pair.of(String.format("/%s/api/orders/status", "qr_order/v1" /*apiPrefix*/), "GET")

        );
        String requestPath = request.getServletPath();
        String requestMethod = request.getMethod();
        for (Pair<String, String> byPassToken : bypassTokens) {
            String pattern = byPassToken.getFirst();
            String method = byPassToken.getSecond();
            if (pathMatcher.match(pattern, requestPath) && requestMethod.equals(method)) {
                return true;
            }
        }
        return false;
    }
}
