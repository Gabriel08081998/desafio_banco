package com.desafio.util;

import org.hibernate.service.spi.InjectService;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UtilCriptografia {

    public static String hashPassword(String password) {
        if (password == null) {
            return null;
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] passwordBytes = password.getBytes("UTF-8");
            byte[] hashedBytes = md.digest(passwordBytes);
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
