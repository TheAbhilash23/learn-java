package com.example.demo.core.security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

@Configuration
public class SecurityConfig {

    @Value("${spring.application.secret-key-password}")
    private String secretKeyPassword;

    @Bean
    public PasswordEncoder passwordEncoder() throws RuntimeException{
        byte[] salt = new byte[32];
        new SecureRandom().nextBytes(salt);
        KeySpec keySpec = new PBEKeySpec(secretKeyPassword.toCharArray(), salt, 4_000_000, 64);
        try{
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] secretKey = secretKeyFactory.generateSecret(keySpec).getEncoded();
            return new Pbkdf2PasswordEncoder(
                    Arrays.toString(secretKey),
                    32,
                    510000,
                    Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256
            );
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }

    }
}
