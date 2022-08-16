package com.example.sample.security.jwt;

import com.example.sample.security.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
@Slf4j
public class JwtUtils {

    private String jwtSecret = "secret";

    private final int jwtExpirationMs = 360000;

    public String generateJwtToken(UserDetailsImpl userPrincipal){
        return this.generateTokenFromUsername(userPrincipal.getUsername());
    }
    public String generateTokenFromUsername(String username){
        return Jwts.builder().setSubject(username).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + this.jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512,this.jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token){
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }


    public boolean validateJwtToken(String authToken){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        }catch (SignatureException e){
            log.error("invalid jwt signature:{}", e.getMessage());
        }catch (MalformedJwtException e){
            log.error("Invalid Jwt token : {}", e.getMessage());
        }catch (ExpiredJwtException e){
            log.error("Jwt token is expired: {}", e.getMessage());
        }catch(UnsupportedJwtException e){
            log.error("jwt token is unsupported: {}", e.getMessage());
        }catch(IllegalArgumentException e){
            log.error("jwt claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
