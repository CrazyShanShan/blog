package org.example.utils;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/***
 * @Description: "jwt utils"
 * @Author: ZBZ
 * @Date: 2021/8/6
 */
public class JwtUtils {

    private static final String JWT_TOKEN = "crazys@edu.whut.cn";

    public static String createToken(Long userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        JwtBuilder jwtBuilder = Jwts.builder()
                                .signWith(SignatureAlgorithm.HS256, JWT_TOKEN)
                                .setClaims(claims)
                                .setIssuedAt(new Date())
                                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000));
        String token = jwtBuilder.compact();
        return token;
    }


    public static Map<String, Object> checkToken(String token) {
        try {
            Jwt parse = Jwts.parser().setSigningKey(JWT_TOKEN).parse(token);
            return (Map<String, Object>) parse.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
