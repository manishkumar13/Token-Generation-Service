package com.service.tokengeneration.runner;

import com.service.tokengeneration.EncryptionStrategyFactory;
import com.service.tokengeneration.TokenGenerationService;
import com.service.tokengeneration.enums.EncryptionStrategyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TokenGenerationRunner implements CommandLineRunner {

    @Autowired
    private EncryptionStrategyFactory encryptionStrategyFactory;

    @Override
    public void run(String... args) throws Exception {
        TokenGenerationService tokenGenerationService = new TokenGenerationService();
        /**
         * Base64 encryption and decryption
         */
        tokenGenerationService.setEncryptionStrategyType(
                encryptionStrategyFactory.getEncryptionStrategy(
                        EncryptionStrategyType.BASE64
                )
        );
        String stringToBeEncrypted = "Manish";
        System.out.println("String to be encrypted : " + stringToBeEncrypted);

        String base64encryptedString = tokenGenerationService.encrypt(stringToBeEncrypted);
        System.out.println("Encrypted string using base64 encryption : " + base64encryptedString);
        String base64decryptedString = tokenGenerationService.decrypt(base64encryptedString);
        System.out.println("Decrypted string using base64 decryption : " + base64decryptedString);

        /**
         * Private key encryption and decryption
         */
        tokenGenerationService.setEncryptionStrategyType(
                encryptionStrategyFactory.getEncryptionStrategy(
                        EncryptionStrategyType.PRIVATE_KEY
                )
        );
        String privateKeyEncryptedString = tokenGenerationService.encrypt(stringToBeEncrypted);
        System.out.println("Encrypted string using PrivateKey encryption : " + privateKeyEncryptedString);
        String privateKeyDecryptedString = tokenGenerationService.decrypt(privateKeyEncryptedString);
        System.out.println("Decrypted string using PrivateKey decryption : " + privateKeyDecryptedString);
    }
}
