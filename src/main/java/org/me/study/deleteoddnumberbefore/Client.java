package org.me.study.deleteoddnumberbefore;

/**
 * 有一个 n 个正整数的数组 a[0, n-1]作为输入，同时生成一个
 * 大小与 a 相同的数组 array，然后依次处理 a 中每个元素：如果当前的 a[i]是奇数则直接添加
 * 到 array 中最后一个元素后面；如果是偶数，则从 array 中最后一个元素开始，向前依次删除所有的奇数。
 */
public class Client {

    public static void main(String[] args) {

        int[] arr = {2, 3, 5, 8};
        //int[] newArr = work(arr);

        int[] newArr = function4(arr);

        for (int i = 0; i < newArr.length; i++) {
            if (newArr[i] != 0)
                System.out.println(newArr[i]);
        }
    }


    private static int[] work(int[] a) {
        int p = 0, n = a.length;
        //创建长度为n的数组,数组每个元素默认为0
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            //当前元素为偶数时,
            if (a[i] % 2 == 0) {
                //将前面所有奇数删除
                while (p >= 0) {
                    if (p == 0) {
                        if (array[p] % 2 != 0) {
                            array[p] = 0;
                        }
                        break;
                    }
                    //前面元素为奇数时
                    if (array[p - 1] % 2 != 0) {
                        //将该元素删除,将该位置元素改为默认值0
                        array[p - 1] = 0;
                        p--;
                    } else {
                        //如果为偶数,不做事情
                        p--;
                    }
                }
            }
            array[p] = a[i];
//            while (p >= 0) {
//                if (array[p] % 2 == 0 && p>0) {
//                    p++;
//                }else{
//                    p
//                }
//            }


            p++;
        }
        return array;
    }

    private static int[] function4 (int[] a ) {
        int p = 0, n = a.length;
        int[] array = new int[n];
        for (int i=0; i<n; i++){
            if (a[i]%2 == 0) //如果是偶数
                while (p>0 && array[p-1]%2!=0)
                    array[p--] = 0; //删除前面的奇数
            array[p++] = a[i];
        }
        return array;
    }
}
