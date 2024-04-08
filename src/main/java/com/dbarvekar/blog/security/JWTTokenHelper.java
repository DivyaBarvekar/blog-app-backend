//package com.dbarvekar.blog.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//@Component
//public class JWTTokenHelper {
//
//    public static final long JWT_TOKEN_VALIDITY = 5*60*60;
//
//    private String secret = "jwtToken";
//
//    public String getUserNameFromToken(String token){
//        return getClaimFromToken(token, Claims::getSubject);
//    }
//
//    public Date getExpirationDate(String token){
//        return getClaimFromToken(token, Claims::getExpiration);
//    }
//
//    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
//        final Claims claims = getAllClaimsFromToken(token);
//        return claimsResolver.apply(claims);
//    }
//
//    //for retrieving any information from token we will need the secret key
//    public Claims getAllClaimsFromToken(String token){
//        return Jwts
//                .parserBuilder()
//                .setSigningKey(secret)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    //check if the token has expired
//    private Boolean isTokenExpired(String token){
//        final Date expiration = getExpirationDate(token);
//        return expiration.before(new Date());
//    }
//
//    //generate token for user
//    public String generateTokenForUser(UserDetails userDetails){
//        Map<String, Object> claims = new HashMap<>();
//        return doGenerateToken(claims, userDetails.getUsername());
//    }
//
//    private String doGenerateToken(Map<String, Object> claims, String subject){
//        return  Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY *1000))
//                .signWith(SignatureAlgorithm.HS512, secret).compact();
//    }
//
//    public Boolean validateToken(String token, UserDetails userDetails){
//        final String userName = getUserNameFromToken(token);
//        return userName.equals(userDetails.getUsername()) &&  !isTokenExpired(token);
//    }
//}
