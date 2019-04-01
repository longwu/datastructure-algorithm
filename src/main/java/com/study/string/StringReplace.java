package com.study.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 将字符串中重复出现的字符串进行替换
 *
 * 将abc2%efg2%1111中的 2% 替换成 ##
 */
public class StringReplace {

    public static void main(String[] args) {
        String input = "abc2%efg2%1111";

        System.out.println(replace(input));

        System.out.println(replace2(input));

        System.out.println(replace3(input));
    }

    /**
     * 读取字符串每一位, 如果发现连续两位是2和%,进行替换
     *
     * 替换的时候需要考虑游标i的变化
     *
     * @param input
     * @return
     */
    private static String replace(String input){
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            //只有两个连续的char为2和%才进行替换
            if (input.charAt(i) == '2' && input.charAt(i + 1) == '%') {
                output += "##";
                i++; //注意因为一次操作了两个char,所以i要多+1一次
            } else {
                output += input.charAt(i);
            }
        }

        return output;
    }

    /**
     * 使用java String内置函数
     * @param input
     * @return
     */
    private static String replace2(String input){
        return input.replaceAll("2%", "##");
    }

    /**
     * 使用java正则进行替换
     *
     * @param input
     * @return
     */
    private static String replace3(String input){
        Pattern p = Pattern.compile("2%");
        Matcher m = p.matcher(input);
        return  m.replaceAll("##");
    }
}
