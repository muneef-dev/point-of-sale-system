package com.devstack.pos.util;

import org.mindrot.jbcrypt.BCrypt;

import java.util.Random;

public class PasswordGenerator {
    private final static String OPTIONS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890@#$";

    public static String passwordGen(int length){ // static varanum object illama use seyya
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(OPTIONS.charAt(new Random().nextInt(64)));
        }
        System.out.println(builder);
        return hashPassword(builder.toString());
    }

    private static String hashPassword(String text) {
        return BCrypt.hashpw(text, BCrypt.gensalt(12));
        // gensalt() -> strength or work-factor (or length)
    }

    public static boolean checkPassword(String password, String hash){
        return BCrypt.checkpw(password,hash);
    }

}
