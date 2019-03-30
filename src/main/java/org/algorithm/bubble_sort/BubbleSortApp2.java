package org.algorithm.bubble_sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author CYX
 * @create 2019-03-30-22:47
 */
public class BubbleSortApp2 {

    public static void main(String[] args) {

        Comparable[] array = new String[]{"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};

        //原数组长度
        int arrayLen = array.length;

        //外层循环
        for (int i = 0; i < arrayLen; i++) {

            //内层循环-下一位比较
            for (int j = i + 1; j < arrayLen; j++) {

                if (array[i].compareTo(array[j]) > 0) {
                    Comparable temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }

        }
        System.out.println(Arrays.toString(array));
    }


}
