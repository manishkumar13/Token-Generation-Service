package com.service.tokengeneration;

import com.service.tokengeneration.enums.EncryptionStrategyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class EncryptionStrategyFactory {

    @Autowired
    @Qualifier("privateKeyEncryption")
    private EncryptionStrategy privateKeyEncryption;

    @Autowired
    @Qualifier("base64Encryption")
    private EncryptionStrategy base64Encryption;

    public EncryptionStrategy getEncryptionStrategy(EncryptionStrategyType encryptionStrategyType) {
        switch (encryptionStrategyType) {
            case BASE64:
                return base64Encryption;
            default:
                return privateKeyEncryption;
        }
    }
}
