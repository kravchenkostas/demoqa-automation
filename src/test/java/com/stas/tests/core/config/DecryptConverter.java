package com.stas.tests.core.config;

import org.aeonbits.owner.Converter;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class DecryptConverter implements Converter<String> {
    private static final String DEFAULT_SECRET = "MySuperSecretKey";

    @Override
    public String convert(Method method, String input) {
        return decrypt(input);
    }

    private String decrypt(String encryptedValue) {
        try {
            String secret = System.getenv("CRYPTO_SECRET");
            if (secret == null || secret.isBlank()) {
                secret = DEFAULT_SECRET;
            }

            SecretKeySpec key = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedValue)), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Failed to decrypt config value", e);
        }
    }
}
