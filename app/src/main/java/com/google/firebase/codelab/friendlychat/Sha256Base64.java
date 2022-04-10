package com.google.firebase.codelab.friendlychat;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256Base64 {

    public static String SHA256 (String text) throws NoSuchAlgorithmException {


        final  MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(text.getBytes());
        /*
        MessageDigest md = MessageDigest.getInstance("SHA256");

        md.reset();
        md.update(text.getBytes());
        byte[] digest = md.digest();*/

        //String encoded = Base64.encode(digest,Base64.DEFAULT).
        return bytesToHex(md.digest());
                //Base64.encodeToString(digest,Base64.NO_WRAP);
    }
    public static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte byt : bytes) result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }

}

