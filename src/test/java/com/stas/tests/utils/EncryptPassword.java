package com.stas.tests.utils;

public class EncryptPassword {
    public static void main(String[] args) {

        String plainPassword = "qwerty";

        String encrypted = CryptoUtils.encrypt(plainPassword);

        System.out.println("Encrypted password:");
        System.out.println(encrypted);
    }
}
