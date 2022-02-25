package com.service.tokengeneration;

import com.service.tokengeneration.exceptions.NullKeyStringException;
import org.springframework.stereotype.Component;

@Component
public class TokenGenerationService {

    private EncryptionStrategy encryptionStrategy;

    public void setEncryptionStrategyType(EncryptionStrategy encryptionStrategy) {
        this.encryptionStrategy = encryptionStrategy;
    }

    public String encrypt(String plainText) throws NullKeyStringException {
        return encryptionStrategy.encrypt(plainText);
    }

    public String decrypt(String cipherText) throws NullKeyStringException {
        return encryptionStrategy.decrypt(cipherText);
    }
}

