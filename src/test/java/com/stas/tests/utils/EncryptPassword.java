package com.stas.tests.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptPassword {
    private static final String DEFAULT_SECRET = "MySuperSecretKey";

    public static void main(String[] args) {

        String plainPassword = "qwerty";
        String encrypted = encrypt(plainPassword);

        System.out.println("Encrypted password:");
        System.out.println(encrypted);
    }

    private static String encrypt(String value) {
        try {
            String secret = System.getenv("CRYPTO_SECRET");
            if (secret == null || secret.isBlank()) {
                secret = DEFAULT_SECRET;
            }

            SecretKeySpec key = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return Base64.getEncoder().encodeToString(cipher.doFinal(value.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            throw new RuntimeException("Failed to encrypt value", e);
        }
    }
}
