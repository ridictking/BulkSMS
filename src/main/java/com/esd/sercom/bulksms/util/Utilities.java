package com.esd.sercom.bulksms.util;


import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class Utilities {

    @Value("${service.email.endpoint}")
    private String emailEndpoint;
    @Value("${uag.endpoint.modify}")
    private String modifyUrl;
    @Value("${uag.endpoint.query}")
    private String queryUrl;
    @Value("${uag.endpoint.verify}")
    private String verificationUrl;

    public String getEmailEndpoint() {
        return emailEndpoint;
    }

    public String getModifyUrl() {
        return modifyUrl;
    }

    public String getQueryUrl() {
        return queryUrl;
    }

    public String getVerificationUrl() {
        return verificationUrl;
    }

    public static String createJWT(String email, long ttlMillis, String token) {
        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("SECRET_KEY");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(UUID.randomUUID().toString())
                .setIssuedAt(now)
                .setSubject(email)
                .setIssuer("Bulksms-9Mobile")
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis > 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        if (token != null) {
            Map<String, Object> headers = new HashMap<>();
            headers.put("token", token);
            builder.setHeader(headers);
        }
        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }
}
