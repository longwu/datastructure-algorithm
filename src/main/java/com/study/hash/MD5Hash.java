package com.study.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Hash {

    public static void main(String[] args) {

        // hash值
        String s = "2222";
        System.out.println(md5HashingAlg(s));
        s = "Steve";
        System.out.println(md5HashingAlg(s));
        s = "Jame";
        System.out.println(md5HashingAlg(s));
        s = "Bing";
        System.out.println(md5HashingAlg(s));

        System.out.println("------------------");
        // hash取模, 得到的值位于0 ~ (mod -1) 之间
        int n = 3;
        s = "jack";
        System.out.println(md5HashingAlg(s) % n);
        s = "Steve";
        System.out.println(md5HashingAlg(s) % n);
        s = "Jame";
        System.out.println(md5HashingAlg(s) % n);
        s = "Bing";
        System.out.println(md5HashingAlg(s) % n);

        System.out.println("------------------");
        //相同的两个string hash值相同
        String a = "Jack";
        String b = "Jack";
        System.out.println(md5HashingAlg(a));
        System.out.println(md5HashingAlg(b));
    }


    /**
     * 使用MD5算法
     * @param key
     * @return
     */
    private static long md5HashingAlg(String key) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            md5.update(key.getBytes());
            byte[] bKey = md5.digest();
            long res = ((long) (bKey[3] & 0xFF) << 24) | ((long) (bKey[2] & 0xFF) << 16) | ((long) (bKey[1] & 0xFF) << 8)| (long) (bKey[0] & 0xFF);
            return res;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return 0l;
    }


}
