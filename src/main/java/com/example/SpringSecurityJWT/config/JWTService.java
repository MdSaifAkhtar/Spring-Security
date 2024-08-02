package com.example.SpringSecurityJWT.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

@Service
public class JWTService {

    private static final  String SECREAT ="hbsjdmdc61sdhbdjbjdjjbded4jhednednuhed7bjdbjdcjbdcejbdcjbjdb13";

    public String generateToken(UserDetails user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("authorities", populateAuthorities(user.getAuthorities()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public String populateAuthorities(Collection<? extends GrantedAuthority> authorities)
    {
        Set<String> authoritiesSet = new HashSet<>();
        for(GrantedAuthority authority : authorities)
        {
            authoritiesSet.add(authority.getAuthority());

        }
        return String.join("," ,authoritiesSet);
        }




    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECREAT);
        return Keys.hmacShaKeyFor(keyBytes);
    }
private Claims extractAllClaims(String token)
{
    return Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJwt(token)
            .getBody();
}

public String extractUsername(String token)
{
    return extractClaim(token ,Claims ::getSubject);
}

public <T> T extractClaim(String token, Function<Claims,T> claimresolver)
{
    final Claims claims= extractAllClaims(token);
    return claimresolver.apply(claims);
}
public boolean isValidToken(String token,UserDetails userDetails)
{
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()));
}

}

