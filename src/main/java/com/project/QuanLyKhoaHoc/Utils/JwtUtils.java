package com.project.QuanLyKhoaHoc.Utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import com.project.QuanLyKhoaHoc.Models.User;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {
    @Value("${jwt.secretKey}")
    private String secretKey;
    private final long expiration = 86400;
    public String generateToken(User user){
        Map<String, Object> claims = new HashMap<>();
        claims.put("userName",user.getUsername());
        try{
            String token = Jwts.builder()
                    .setClaims(claims)
                    .setSubject(user.getUsername())
                    .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000l))
                    .signWith(getSignKey(),SignatureAlgorithm.HS256)
                    .compact();
            return token;
        }catch (Exception e){
            throw new InvalidParameterException("cant not create jwt : " + e.getMessage());
        }
    }
    private Key getSignKey(){
        byte[] bytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(bytes);
    }
    private Claims extractAllClaims(String token ){
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public <T> T extractClaims(String token, Function<Claims, T> claimsResolve){
        final Claims claims = this.extractAllClaims(token);
        return claimsResolve.apply(claims);
    }
    public boolean tokenExpired(String token){
        Date expirationDate = this.extractClaims(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }
    public boolean validation(String token, UserDetails userDetails){
        String phoneNumber = extractClaims(token,Claims::getSubject);
        return (phoneNumber.equals(userDetails.getUsername())) && !tokenExpired(token);
    }
}
