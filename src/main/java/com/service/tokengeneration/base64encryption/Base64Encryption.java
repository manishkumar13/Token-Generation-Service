package com.service.tokengeneration.base64encryption;

import com.service.tokengeneration.EncryptionStrategy;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class Base64Encryption implements EncryptionStrategy {

    @Override
    public String encrypt(String plainText) {
        String basicBase64format = Base64.getEncoder().encodeToString(plainText.getBytes());
        return basicBase64format;
    }

    @Override
    public String decrypt(String cipherText) {
        byte[] actualByte = Base64.getDecoder().decode(cipherText);
        String actualString = new String(actualByte);
        return actualString;
    }
}
