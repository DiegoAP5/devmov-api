package casasegura.devmov.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

import java.util.Date;
import java.util.Map;

public class JWTUtils {

    public static String generateToken(String jwtSecret, String email, Map<String, Object> payload){
        Date expirationTime = new Date(System.currentTimeMillis()+ 1000L*60*60000000);

        return Jwts.builder()
                .setSubject(email)
                .addClaims(payload)
                .setIssuedAt(new Date())
                .setExpiration(expirationTime)
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .compact();
    }

    public static Boolean isValidateToken(String jwtSecret,String token){
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes()).build().parseClaimsJws(token);
            return Boolean.TRUE;
        }catch (Exception exception){
            throw new AuthenticationCredentialsNotFoundException("Token has expired");
        }
    }

    public static String getEmailFromJWT(String jwtSecret, String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecret.getBytes()).build()
                .parseClaimsJws(token).getBody();

        return claims.getSubject();
    }
}
