package com.ecom.web.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class CryptoUtilsTest {

    @Test
    public void test() {
        String encryptedPasswd = CryptoUtils.encrypt("prasanna123", "SHA1", "UTF-8");
        assertNotNull(encryptedPasswd);
        
        String encryptedPasswd2 = CryptoUtils.encrypt("prasanna123", "SHA1", "UTF-8");
        
        assertTrue(encryptedPasswd.equals(encryptedPasswd2));
        
        String encryptedPasswd3 = CryptoUtils.encrypt("prasanna123 ", "SHA1", "UTF-8");
        assertFalse(encryptedPasswd.equals(encryptedPasswd3));
    }

}
