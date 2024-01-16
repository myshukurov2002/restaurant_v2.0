package com.company.util;


import com.company.model.enums.ProfileRole;
import com.company.expection.exp.UnAuthorizedException;
import com.company.model.dto.response.JwtResponse;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class JWTUtil {
    @Value("${jwt.expiry}")
    private static Integer expiry = 10*3600*1000;

    @Value("${jwt.secret}")
    private static String secret = "!mazgi_pazgi234^sad***-*-*+*-0293nd42839+nyf423gikeyodun298143ydrijom!mh9l8e]fh";

    public static String encode(UUID id, List<ProfileRole> roles) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.signWith(SignatureAlgorithm.HS512, secret);

        jwtBuilder.claim("id", id);
        jwtBuilder.claim("roles", roles);

        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (expiry)));
        jwtBuilder.setIssuer("restaurant PORTAL");
        return jwtBuilder.compact();
    }
    public static JwtResponse decode(String token) {
        try {
            JwtParser jwtParser = Jwts.parser();
            jwtParser.setSigningKey(secret);

            Jws<Claims> jws = jwtParser.parseClaimsJws(token);

            Claims claims = jws.getBody();

            String id = (String) claims.get("id");
            List<ProfileRole> roles = (List<ProfileRole>) claims.get("roles");

            return new JwtResponse(UUID.fromString(id), roles);
        } catch (JwtException e) {
            throw new UnAuthorizedException("Your session expired");
        }
    }
}