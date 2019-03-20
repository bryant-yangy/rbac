package com.westos.rbac.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(md5("990304"));
    }
    public static String md5(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            md5.update(str.getBytes());
            byte[] digest = md5.digest();
            return toHexString(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String str = "0123456789abcdef";

    private static String toHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(32);
//        System.out.println(b); // -31  ==> e1
        // 高4位  0x0f & (b >> 4)
        // 低4位  0x0f & b
        for (byte b : bytes) {
            sb.append(str.charAt(0x0f & (b >> 4)));
            sb.append(str.charAt(0x0f & b));
        }
        return sb.toString();
    }

}
