package com.study.string;

/**
 * 实现一个字符串toUpperCase()大写算法
 */
public class ToUpperCase {
    public static void main(String[] args) {

        String str = "abcdefghijklmnopqrstuvwxyz";
        System.out.println(toUpperCase(str));
    }

    /**
     * 将字符串转换成char数组
     *
     * 遍历数组, 将每个小写字母转换成大写
     *
     * 利用ascii大小写字母对应的差值进行转换
     *
     * @param str
     * @return
     */
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
