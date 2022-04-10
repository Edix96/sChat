package com.google.firebase.codelab.friendlychat;
import java.sql.SQLOutput;
import java.util.Arrays;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Hmac {

    static public byte[] calcHmacSha256(byte[] secretKey, byte[] message) {
        String chave = "segredo";
        secretKey = chave.getBytes();
        byte[] hmacSha256 = null;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, "HmacSHA256");
            mac.init(secretKeySpec);
            hmacSha256 = mac.doFinal(message);
        } catch (Exception e) {
            throw new RuntimeException("Failed to calculate hmac-sha256", e);
        }
        return hmacSha256;
    }
    static public boolean verifyHmacSha256(byte[] secretKey, byte[] message, byte [] hmac) {
        boolean verify = false;
        String chave = "segredo";
        secretKey = chave.getBytes();
        byte[] hmc = null;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, "HmacSHA256");
            mac.init(secretKeySpec);
            hmc = mac.doFinal(message);
            String s = new String(hmc);
            String r = new String(hmac);
            System.out.println("SSSSSSSSSSSSSS"+s);
            System.out.println("rrrrrrrrrrrrrr"+r);
            if(s.equals(r)){
                verify=true;
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to calculate hmac-sha256", e);
        }
        return verify;
    }


}
