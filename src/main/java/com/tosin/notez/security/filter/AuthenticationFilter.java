package com.tosin.notez.security.filter;

import com.tosin.notez.security.Principal;
import com.tosin.notez.security.service.JwtService;
import com.tosin.notez.security.service.TokenDetail;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Service
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {


    private final JwtService jwtService;

    /**
     * Same contract as for {@code doFilter}, but guaranteed to be just invoked once per request within a single request
     * thread. See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String jwtString = jwtService.extractJwtFromRequest();

        if (StringUtils.hasText(jwtString)) {

            TokenDetail tokenDetail = jwtService.extractTokenDetail(jwtString);
            UserDetails userDetails = Principal.create(tokenDetail);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        } else {

            logger.info("Received request without Authorization Header");
        }
        filterChain.doFilter(request, response);
    }
}
