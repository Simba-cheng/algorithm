package org.algorithm.binarysearch;

/**
 * 二分查找算法 - 递归实现
 *
 * @author CYX
 * @create 2019-04-12-20:26
 */
public class BinarySearchApp3 {

    /**
     * 有序的序列
     */
    public static final int[] array = {2, 4, 5, 7, 8, 9, 13, 23, 34, 45};

    public static void main(String[] args) {

        int start = 0;
        int end = array.length - 1;

        int key = 23;

        int middle = query(array, start, end, key);
        System.out.println("middle：" + middle);
    }

    /**
     * 递归实现 二分查找
     *
     * @param array 有序数组
     * @param start 起始下标
     * @param end   结束下标
     * @param key   查找的键值
     */
    public static int query(int[] array, int start, int end, int key) {

        if (start > end) {
            return -1;
        }

        int middle = (start + end) / 2;

        if (array[middle] > key) {
            //往左
            return query(array, start, middle - 1, key);
        } else if (array[middle] < key) {
            //往右
            return query(array, middle + 1, end, key);
        } else {
            return middle;
        }
    }

}
