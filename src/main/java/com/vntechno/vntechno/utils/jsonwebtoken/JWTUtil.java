package com.vntechno.vntechno.utils.jsonwebtoken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTUtil {
    // Khóa bí mật để ký và xác thực JWT. Bạn nên sử dụng một khóa bí mật mạnh hơn và lưu trữ nó một cách an toàn.
    private String SECRET_KEY = "VNTECHNO_JWT_SECRET_KEY";

    // Trích xuất tên người dùng (username) từ JWT
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Trích xuất thời gian hết hạn từ JWT
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Trích xuất một claim cụ thể từ JWT
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Trích xuất tất cả các claims từ JWT
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    // Kiểm tra xem JWT có hết hạn hay không
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Tạo JWT từ tên người dùng
    public String generateToken(String userId, String email, String roleType) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        claims.put("userId", userId);
        claims.put("roles", roleType); // Thêm quyền vào payload
        return createToken(claims, userId);
    }

    // Tạo JWT với các claims và tên người dùng
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Thời gian hết hạn là 10 giờ
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // Sử dụng thuật toán HS256 và khóa bí mật để ký JWT
                .compact();
    }

    // Xác thực JWT với tên người dùng
    public Boolean validateToken(String token, String username) {
        final String tokenUsername = extractUsername(token);
        return (tokenUsername.equals(username) && !isTokenExpired(token));
    }
}
