package org.algorithm.binarysearch;

/**
 * 二分查找
 * <p>
 *
 * <pre>
 *      查找第一个与key相等的元素
 *      查找第一个相等的元素，也就是说等于查找key值的元素有好多个，返回这些元素最左边的元素下标。
 * </pre>
 *
 * @author CYX
 * @date: 2019/4/13 15:06
 */
public class BinarySearchApp4 {

    /**
     * 有序的序列 - 数据会重复
     */
    public static final int[] array = {2, 4, 5, 7, 8, 4, 45, 9, 13, 23, 34, 45};

    public static void main(String[] args) {

        int key = 4;

        int middle = query(array, key);

        System.out.println("middle : " + middle);

    }

    public static int query(int[] array, int key) {

        int start = 0;
        int end = array.length;

        while (start <= end) {

            int middle = (start + end) / 2;

            if (array[middle] <= key) {
                //右
                start = middle + 1;
            } else if (array[middle] >= key) {
                //左
                end = middle - 1;
            }

            /**
             * 第一个判断没啥说的，start下标小于数组总长
             *
             * 第二个，判断start下标在array中数值是否等于key，注意是start而不是middle
             */
            if (start < array.length && array[start] == key) {
                return start;
            }
        }
        return -1;
    }
}
