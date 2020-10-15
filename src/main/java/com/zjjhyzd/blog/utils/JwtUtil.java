package com.zjjhyzd.blog.utils;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.common.collect.Lists;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;

public class JwtUtil {
    // 过期时间为1天
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;
    private static final String TOKEN_SECRET = "$%@-1af7-a-cff-99e3-4c35-bc10-3d3a75bb54fc";

    public static String sign(String username, String... authorities) {
        return sign(username, Lists.newArrayList(authorities));
    }

    public static String sign(String username, Collection<? extends GrantedAuthority> authorities) {
        ArrayList<String> list = Lists.newArrayList();
        authorities.forEach(authority -> {
            list.add(authority.getAuthority());
        });
        return sign(username, list);
    }

    public static String sign(String username, List<String> authorities) {
        // 过期时间
        Date expireDt = new Date(System.currentTimeMillis() + EXPIRE_TIME);

        // 私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        // 设置头部信息
        Map<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        return JWT.create().withHeader(header).withSubject(username).withExpiresAt(expireDt).withClaim("username", username)
                .withClaim("authorities", JSON.toJSONString(authorities)).sign(algorithm);
    }

    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean   expired(String token){
        return JWT.decode(token).getExpiresAt().before(new Date());
    }

    public static String getUsername(String token) {
        if (verify(token)) {
            return JWT.decode(token).getSubject();
        } else {
            return null;
        }
    }

    public static List<String> getAuthorities(String token) {
        if (verify(token)) {
            return JSON.parseArray(JWT.decode(token).getClaim("authorities").asString(), String.class);
        } else {
            return null;
        }
    }
}
