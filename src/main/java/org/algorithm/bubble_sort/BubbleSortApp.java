package org.algorithm.bubble_sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author CYX
 * @create 2019-03-30-22:46
 */
public class BubbleSortApp {

    public static void main(String[] args) {

        int[] array = new int[]{5, 2, 7, 6, 4, 1, 3, 9, 8};

        //原数组长度
        int arrayLen = array.length;

        //外层循环
        for (int i = 0; i < arrayLen; i++) {
            //内层循环
            for (int j = i + 1; j < arrayLen; j++) {
                //外层小于内层
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(array));
    }


}
