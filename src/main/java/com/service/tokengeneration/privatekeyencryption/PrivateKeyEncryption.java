package com.service.tokengeneration.privatekeyencryption;

import com.service.tokengeneration.EncryptionStrategy;
import com.service.tokengeneration.exceptions.NullKeyStringException;
import org.apache.commons.codec.binary.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


@Component
public class PrivateKeyEncryption implements EncryptionStrategy {

    @Value("${external.encryption.key}")
    private String keyString;

    private static Key getKey(String keyString) throws NoSuchAlgorithmException {
        byte[] key = keyString.getBytes(StandardCharsets.UTF_8);
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16);
        return new SecretKeySpec(key, "AES");
    }

    @Override
    public String encrypt(String plainText) throws RuntimeException, NullKeyStringException {
        try {
            if (!ObjectUtils.isEmpty(keyString)) {
                Cipher cipher;
                try {
                    cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
                    cipher.init(Cipher.ENCRYPT_MODE, getKey(keyString));
                    return Base64.encodeBase64String(cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8)));
                } catch (Exception e) {
                    throw new RuntimeException("Error while encrypting: " + plainText, e);
                }
            } else {
                throw new NullKeyStringException("Key String Not Found");
            }
        } catch (NullKeyStringException nullKeyStringException) {
            throw nullKeyStringException;
        }
    }

    @Override
    public String decrypt(String cipherText) throws RuntimeException, NullKeyStringException {
        try {
            if (!ObjectUtils.isEmpty(keyString)) {
                Cipher cipher;
                try {
                    cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
                    cipher.init(Cipher.DECRYPT_MODE, getKey(keyString));
                    return new String(cipher.doFinal(Base64.decodeBase64(cipherText)), StandardCharsets.UTF_8);
                } catch (Exception e) {
                    throw new RuntimeException("Error while decrypting " + cipherText, e);
                }
            } else {
                throw new NullKeyStringException("Key String Not Found");
            }
        } catch (NullKeyStringException nullKeyStringException) {
            throw nullKeyStringException;
        }
    }
}
