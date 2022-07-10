package com.lingxi.lingxi_java.utils;

import org.springframework.security.crypto.codec.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {
    public static String md5(String text) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("加载加密算法失败");
        }
        byte[] bytes = messageDigest.digest(text.getBytes());
        return String.valueOf(Hex.encode(bytes));
    }
}
