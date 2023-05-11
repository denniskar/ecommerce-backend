package com.ecommerce.agroproducts.authentication;
import com.ecommerce.agroproducts.utils.responses.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomJwtAuthenticationFilter extends OncePerRequestFilter {

    private final Logger LOGGER = LoggerFactory.getLogger(CustomJwtAuthenticationFilter.class);
    private final ObjectMapper mapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public CustomJwtAuthenticationFilter(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) {


        try {

            //  LOGGER.info("host {}  HttpMethod: {}, URI : {},Token : {}", req.getHeader("host"), req.getMethod(), req.getRequestURI(), req.getHeader("X-Authorization"));

            String token = jwtTokenUtil.resolveToken(req);
            if (token != null && jwtTokenUtil.validateToken(token)) {
                Authentication auth = jwtTokenUtil.getAuthentication(token);

                if (auth != null) {
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }


            filterChain.doFilter(req, res);
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException | UnsupportedJwtException | IllegalArgumentException | ServletException | IOException e) {


            String message = e.getLocalizedMessage();


            if (e instanceof SignatureException) {
                message = "Invalid token signature";

            } else if (e instanceof ExpiredJwtException) {
                message = "Expired token";
            } else if (e instanceof UnsupportedJwtException) {
                message = "Unsupported token";
            } else if (e instanceof IllegalArgumentException) {
                message = "JWT claims string is empty";
            } else if (e instanceof MalformedJwtException) {

                message = "Malformed JWT token";
            } else {
                message = "Invalid token";
            }

            LOGGER.error("Error", e);


            res.setStatus(HttpStatus.FORBIDDEN.value());
            res.setContentType("application/json");
            try {
                String resp = mapper.writeValueAsString(ApiResponse.response(1,message));
                res.getWriter().print(resp);
            } catch (IOException ex) {
                LOGGER.error("Error occurred", ex);
            }
        }


    }


}