package com.aws.cognito.tutorial.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.RSAKeyProvider;
import com.aws.cognito.tutorial.security.services.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtProvider {

    @Value(value = "${aws.cognito.identityPoolUrl}")
    private String identityPoolUrl;

    @Value(value = "${aws.cognito.region}")
    private String region;

    @Value(value = "${aws.cognito.issuer}")
    private String issuer;

    private static final String USERNAME = "username";

    public DecodedJWT getDecodedJwt(String token) {
        String tokenWithoutBearer = token.startsWith("Bearer ") ? token.substring("Bearer ".length()) : token;
        RSAKeyProvider keyProvider = new AwsCognitoRSAKeyProvider(region, identityPoolUrl);
        Algorithm algorithm = Algorithm.RSA256(keyProvider);
        JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
        verifier.verify(tokenWithoutBearer);
        return verifier.verify(tokenWithoutBearer);
    }


    public String getUserNameFromToken(String token) {
        DecodedJWT jwt = getDecodedJwt(token);
        String userName = jwt.getClaim(USERNAME).toString();
        return userName.replace("\"","");
    }

    public boolean validateToken(String token) {
        try {
            getDecodedJwt(token);
            return true;
        } catch (JWTVerificationException exception) {
            log.error("Validate token failed: " + exception.getMessage());
        }
        return false;
    }


}
