package com.proyectoacceso02ev;

import java.security.SecureRandom;
import java.util.Base64;

public class SecretKeyGenerator {
    //Programa que al ejecutarlo te genera una secret key
    public static void main(String[] args) throws Exception {

        SecureRandom secureRandom = new SecureRandom();

        byte[] secretKeyBytes = new byte[32];
        secureRandom.nextBytes(secretKeyBytes);

        String SECRET_KEY = Base64.getEncoder().encodeToString(secretKeyBytes);

        System.out.println(SECRET_KEY);

    }
}
