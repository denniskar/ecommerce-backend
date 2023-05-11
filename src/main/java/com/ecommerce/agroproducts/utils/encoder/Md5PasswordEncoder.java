//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ecommerce.agroproducts.utils.encoder;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Md5PasswordEncoder {
    public Md5PasswordEncoder() {
    }

    public static String encode(String rawIput) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(rawIput.getBytes());
        BigInteger no = new BigInteger(1, messageDigest);

        String hashtext;
        for(hashtext = no.toString(16); hashtext.length() < 32; hashtext = "0" + hashtext) {
        }

        return hashtext;
    }

    public static String hashPasswordByMd5(String username, String password, String timestamp) throws Exception {
        return encode(String.format("%s%s%s", username, password, timestamp));
    }
}
