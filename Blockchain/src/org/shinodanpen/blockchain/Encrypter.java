package org.shinodanpen.blockchain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypter {

    public static String encryptSHA256(String data){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] output = md.digest(data.getBytes());

            return toHex(output);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String toHex(byte[] input){
        StringBuilder sb = new StringBuilder();

        for (byte b : input){
            sb.append(Integer.toHexString(0xFF & b));
        }

        return sb.toString();
    }

}
