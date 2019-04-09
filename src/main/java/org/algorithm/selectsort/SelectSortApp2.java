package org.algorithm.selectsort;

import java.util.Arrays;

/**
 * 选择排序 - 简化版
 *
 * @author CYX
 * @create 2019-03-30-22:55
 */
public class SelectSortApp2 {

    public static void main(String[] args) {

        String[] a = new String[]{"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};

        //排序
        sort(a);

        //确认排序后数组是否有序
        boolean isSortResult = isSorted(a);
        System.out.println();
        System.out.println("isSortResult : " + isSortResult);
        System.out.println();

        //输出
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }

    }


    public static void sort(Comparable[] a) {
        //将a[]按照升序排列

        //数组长度
        int N = a.length;

        for (int i = 0; i < N; i++) {

            //将a[i] 和 a[i+1 ... N]中最小的元素交换

            //最小元素的索引
            int min = i;

            System.out.println("i : " + i + " , min : " + min + " , arr : " + Arrays.toString(a));

            for (int j = i + 1; j < N; j++) {

                if (a[j].compareTo(a[min]) < 0) {
                    min = j;
                }

                Comparable t = a[i];
                a[i] = a[min];
                a[min] = t;
            }
        }

    }

    /**
     * 确认排序后数组元素是否是有序的
     *
     * @param a
     * @return
     */
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i].compareTo(a[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }

}
