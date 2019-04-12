package org.algorithm.binarysearch;

/**
 * 二分查找算法 - 迭代实现
 * <p>
 * https://www.cnblogs.com/QG-whz/p/5194627.html
 *
 * @author CYX
 * @create 2019-04-12-20:06
 */
public class BinarySearchApp2 {

    /**
     * 有序的序列
     */
    public static final int[] array = {2, 4, 5, 7, 8, 9, 13, 23, 34, 45};

    public static void main(String[] args) {

        int key = 2;

        int mid = query(array, key);
        System.out.println("mid : " + mid);
    }

    /**
     * 二分查找
     *
     * <pre>
     *     设置三个索引：
     *          start指向数组待查范围的起始元素；
     *          end指向数组待查范围的最后一个元素；
     *          middle=(start+end)/2。
     *
     *          开始时待查范围为整个数组。
     *
     *      比较arrty[middle]与查找元素的关系：
     *          如果array[middle]等于查找元素，则查找成功
     *          如果array[middle]大于查找元素，则说明待查元素在数组的前半部分，此时缩小待查范围，令end = middle-1
     *          如果array[middle]小于查找元素，则说明待查元素在数组的后半部分，此时缩小待查范围，令start = middle +1
     *
     *      重复执行前面两步，直到array[middle ] 等于查找元素则查找成功或start>end查找失败。
     * </pre>
     *
     * @param array
     * @param key
     */
    public static int query(int[] array, int key) {

        //起始下标 指向数组待查范围的起始元素
        int start = 0;

        //结束下标 指向数组待查范围的最后一个元素
        int end = array.length;

        //可以看做指针
        int middle;

        while (start <= end) {

            middle = (end + start) / 2;

            if (array[middle] < key) {
                //中间值小于key，往右查找
                start = middle + 1;

            } else if (array[middle] > key) {
                //中间值大于key，往左查找
                end = middle - 1;

            } else {
                return middle;
            }
        }
        return -1;
    }

}
