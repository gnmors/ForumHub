package com.Forumhub.demo.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class EncryptionBCryptUtil {

    public static String encrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
