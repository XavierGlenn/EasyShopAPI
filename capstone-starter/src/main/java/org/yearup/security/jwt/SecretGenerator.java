package org.yearup.security.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

    public class SecretGenerator {
        public static void main(String[] args) {
            byte[] key = Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded();
            System.out.println(java.util.Base64.getEncoder().encodeToString(key));
        }
    }