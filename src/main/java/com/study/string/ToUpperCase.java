package com.study.string;

/**
 * 实现一个字符串toUpperCase()大写算法
 */
public class ToUpperCase {
    public static void main(String[] args) {

        String str = "abcdefghijklmnopqrstuvwxyz";
        System.out.println(toUpperCase(str));
    }

    private static String toUpperCase(String str){
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i ++){
            if(chars[i] >= 97 && chars[i] <= 122){
                chars[i] -= 32;
            }
        }
        return new String(chars);
    }
}
