package com.huchengzhen.goserver.security;


import com.huchengzhen.goserver.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;

@Component
public class JwtTokenUtils {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    //秘钥

    //签发者
    private static final String ISS = "long";

    // 角色的key
    public static final String ROLE_CLAIMS = "rol";

    public static final String ID = "id_dummy";

    // 过期时间是3600秒，既是1个小时
    private static final long EXPIRATION = 3600L;

    // 选择了记住我之后的过期时间为7天
    private static final long EXPIRATION_REMEMBER = 604800L;

    @Value("${WeBlogJwtSecret:jwtSECRET}")
    private String jwtSecret;

    private JwtParser jwtParser;

    @PostConstruct
    private void initJwtParser() {
        jwtParser = Jwts.parser()
                .setSigningKey(jwtSecret);
    }

    // 创建token
    public String createToken(User user) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(ROLE_CLAIMS, user.getRole());
        map.put(ID, user.getId().toString());
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .setClaims(map)
                .setIssuer(ISS)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_REMEMBER * 1000))
                .compact();
    }

    // 从token中获取用户名
    public String getUsername(Claims claims) {
        return claims.getSubject();
    }

    // 获取用户角色
    public String getUserRole(Claims claims) {
        return (String) claims.get(ROLE_CLAIMS);
    }

    public Long getId(Claims claims) {
        return Long.parseLong((String) claims.get(ID));
    }

    // 是否已过期
    public boolean isExpiration(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

    public Claims getTokenBody(String token) {
        return jwtParser
                .parseClaimsJws(token)
                .getBody();
    }
}