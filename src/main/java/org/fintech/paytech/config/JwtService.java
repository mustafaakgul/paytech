package org.fintech.paytech.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.fintech.paytech.domain.core.user.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

// Can be used own method to write customs
// This is service cuz to pass jwt secret but not static passing
@Service
public class JwtService {

    // @Value("${jwt.secret}")
    // Min 32 characters long secret key
    @Value("ssshhhdasdasdasfsaadsdasgadasdasdagasdasdasdagasdssshhhdasdasdasfsaadsdasgadasdasdagasdasdasdagasd") // TODO: Replace with actual secret from application properties
    private String secret;

    public String extractEmail(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }

    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String jwtToken) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    /*private SecretKey getSigningKey() {
        byte [] bytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(bytes);
    }*/
    private SecretKey getSigningKey() {
        byte [] bytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(bytes);
    }

    public boolean isTokenValid(String jwtToken, UserDetails userDetails) {
        final String email = extractEmail(jwtToken);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken));
    }

    private boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    private Date extractExpiration(String jwtToken) {
        return extractClaim(jwtToken, Claims::getExpiration);
    }

    public String generateToken(User user) {
        return createToken(user.getEmail());
    }

    private String createToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                //.signWith(getSigningKey())
                 .signWith(SignatureAlgorithm.HS512, secret)
                 //.signWith(Keys.secretKeyFor(SignatureAlgorithm.HS512), secret)
                .compact();
    }
}
