package com.ecommerce.agroproducts.authentication;
import com.ecommerce.agroproducts.service.CustomUserDetailsService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Base64;
import java.util.Date;


@Service
@Scope("singleton")
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;
    private final static String secret_key = "3QFJYBGJ0IOOU4I04TP31G2EA07B6VLTH8WZM8AG1ROAYA31KI";

    @Autowired
    @Qualifier("customUserDetailsService")
    private CustomUserDetailsService customUserDetailsService;

    public String resolveToken(HttpServletRequest req) {

        String bearerToken = req.getHeader("X-Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }


    public boolean validateToken(String token) throws IllegalArgumentException, SignatureException, MalformedJwtException, ExpiredJwtException, UnsupportedJwtException {

        Jws<Claims> claims = Jwts.parser().setSigningKey(encodeSecretKey(secret_key)).parseClaimsJws(token);

        return !claims.getBody().getExpiration().before(new Date());

    }


    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(encodeSecretKey(secret_key)).parseClaimsJws(token).getBody().getSubject();
    }


    public String createToken(String username, Date now, Date validity) {


        Claims claims = Jwts.claims().setSubject(username);
        String secretKey = encodeSecretKey(secret_key);

        return Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)
                //
                .signWith(SignatureAlgorithm.HS512, secretKey)//sign with secretKey
                .compact();
    }


    public String encodeSecretKey(String secretKey) {
        return Base64.getEncoder().encodeToString(secretKey.getBytes());

    }

    public String getUsernameFromToken(HttpServletRequest req) throws Exception {
        return getUsername(resolveToken(req));

    }
}

