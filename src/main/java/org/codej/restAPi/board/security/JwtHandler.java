package org.codej.restAPi.board.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtHandler {

    private final String type = "Bearer "; //생성해낸 토큰이 어떤 타입인지


    public String createToken(String encodedKey, String subject, long maxAgeSeconds){
        Date now = new Date();
        return type + Jwts.builder() // jwt 빌드
                .setSubject(subject) // 토큰에 저장할 데이터를 지정
                .setIssuedAt(now) // 토큰 발급일 지정
                .setExpiration(new Date(now.getTime() + maxAgeSeconds*1000L)) // 토큰 만료 일자
                .signWith(SignatureAlgorithm.HS256,encodedKey) // 파라미터로 받은 key로 SHA-256알고리즘 사용하여 서명
                .compact(); //주어진 정보로 토큰을 생성
    }
    public String extractSubject(String encodedKey, String token){ // 토큰에서 subject를 추출
        return parse(encodedKey,token).getBody().getSubject(); // 토큰을 파싱하고 바디에서 subject를 꺼낼 수 있다.
    }
    public boolean validate(String encodedKey, String token){//토큰의 유효성 검증
        try {
            parse(encodedKey,token); // 파싱과정중 jwt 관련 예외가 발생하면 유효하지 않은 토큰으로 판단
            return true;
        } catch (JwtException e){
            return false;
        } catch (Exception e){
            return false;
        }
    }
    public Jws<Claims>parse(String key,String token){// JWts의 parse()를 이용하여 key를 지정해주고,파싱수행
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(unType(token));
    }
    public String unType(String token){ // 이때 토큰 문자열에는 토큰의 타입도 포함되어있음로 unType메서드를 이용하여 제거
        return token.substring(type.length());
    }
}
