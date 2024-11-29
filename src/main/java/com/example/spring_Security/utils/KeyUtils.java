package com.example.spring_Security.utils;

import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

@Service
public class KeyUtils {
    public String generateSecretKey() {
        try {
            // USE the HMAC-SHA256 algorithm for key generation
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            keyGenerator.init(256); // set the key size to (256 bits)
            SecretKey secretKey = keyGenerator.generateKey();

            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (Exception e) {
            throw new RuntimeException("Error generating secret key", e);
        }
    }
}
