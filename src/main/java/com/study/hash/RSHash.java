package com.study.hash;

public class RSHash {

    public static void main(String[] args) {
        // hash值
        String s = "jack";
        System.out.println(rsHash(s));
        s = "Steve";
        System.out.println(rsHash(s));
        s = "Jame";
        System.out.println(rsHash(s));
        s = "Bing";
        System.out.println(rsHash(s));

        System.out.println("------------------");
        // hash取模, 得到的值位于0 ~ (mod -1) 之间
        int n = 3;
        s = "jack";
        System.out.println(rsHash(s) % n);
        s = "Steve";
        System.out.println(rsHash(s) % n);
        s = "Jame";
        System.out.println(rsHash(s) % n);
        s = "Bing";
        System.out.println(rsHash(s) % n);

        System.out.println("------------------");
        //相同的两个string hash值相同
        String a = "Jack";
        String b = "Jack";
        System.out.println(rsHash(a));
        System.out.println(rsHash(b));
    }

    /**
     * RSHash算法
     *
     * @param str
     * @return
     */
    static int rsHash(String str) {
        int b = 378551;
        int a = 63689;
        int hash = 0;

        for (int i = 0; i < str.length(); i++) {
            hash = hash * a + str.charAt(i);
            a = a * b;
        }
        return (hash & 0x7FFFFFFF);
    }
}
