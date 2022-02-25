package com.service.tokengeneration;

import com.service.tokengeneration.exceptions.NullKeyStringException;

public interface EncryptionStrategy {

    String encrypt(String plainText) throws NullKeyStringException;

    String decrypt(String cipherText) throws NullKeyStringException;
}

