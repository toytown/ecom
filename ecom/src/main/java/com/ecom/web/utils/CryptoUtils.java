package com.ecom.web.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoUtils {

    public static final String DEFAULT_ALGORITHM="SHA1";
    private static final String DEFAULT_ENCODING="UTF-8";
    public static final String MD5_ALGORITHM="MD5";
    
    
    public static final synchronized String encrypt(String plaintext, String algorithm, String encoding) {
        
        MessageDigest msgDigest = null;
        String hashValue = null;
        
        try {
            msgDigest = MessageDigest.getInstance(algorithm);
            msgDigest.update(plaintext.getBytes(encoding));
            byte rawByte[] = msgDigest.digest();
            hashValue = String.valueOf(Base64Coder.encode(rawByte));
 
        } catch (NoSuchAlgorithmException e) {
            System.err.println("No Such Algorithm Exists");
        } catch (UnsupportedEncodingException e) {
            System.err.println("The Encoding Is Not Supported");
        }
        
        return hashValue;
    } 
    
    public final static synchronized String encrypt(String plaintext) {
        
        MessageDigest msgDigest = null;
        String hashValue = null;
        
        try {
            msgDigest = MessageDigest.getInstance(DEFAULT_ALGORITHM);
            msgDigest.update(plaintext.getBytes(DEFAULT_ENCODING));
            byte rawByte[] = msgDigest.digest();
            hashValue = String.valueOf(Base64Coder.encode(rawByte));
 
        } catch (NoSuchAlgorithmException e) {
            System.err.println("No Such Algorithm Exists");
        } catch (UnsupportedEncodingException e) {
            System.err.println("The Encoding Is Not Supported");
        }
        
        return hashValue;
    }    
}
