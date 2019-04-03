package org.algorithm.selectsort;

import java.util.Arrays;

/**
 * 初级排序算法 - 选择排序
 *
 * <pre>
 *     首先，找到数组中最小的那个元素，其次，将它和数组的第一个元素交换位置（如果第一个元素就是最小元素那么它就和自己交换）。
 *
 *     再次，在剩下的元素中找到最小的元素，将它与数组的第二个元素交换位置。
 *
 *     如此往复，直到将整个数组排序。
 *
 *     这种方法叫做选择排序，因为它在不断地选择剩余元素之中的最小者。
 * </pre>
 *
 * @author CYX
 * @create 2019-03-30-22:54
 */
public class SelectSortApp {

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
        show(a);

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
                if (less(a[j], a[min])) {
                    min = j;
                }
                exch(a, i, min);
            }
        }
    }

    /**
     * 对元素进行比较
     *
     * @param v
     * @param w
     * @return
     */
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * 将元素交换位置
     *
     * @param a
     * @param i
     * @param j
     */
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * 打印数组
     *
     * @param a
     */
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
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
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

}
