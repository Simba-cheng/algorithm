package org.algorithm.binarysearch;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 二分查找算法
 *
 * <pre>
 *     接收一个整数键和一个已经有序的int数组作为参数
 *
 *     如果该键存在于数组中，则返回它的索引，否则返回-1。
 *
 *     算法使用两个变量lo和hi，并保证如果键在数组中则它一定在a[lo..hi]中，
 *     然后方法进入一个循环，不断将数组的中间键（索引为mid）和被查找的键比较。
 *
 *     如果被查找的键等于a[mid]，返回mid；否则算法就将查找范围缩小一半，
 *     如果被查找的键小于a[mid]就继续在左半边查找，如果被查找的键大于a[mid]就继续在右半边查找。
 * </pre>
 *
 * @author CYX
 * @create 2019-03-30-22:56
 */
public class BinarySearchApp {

    private static final int NUMBER_LENGTH_TEST = 10000000;

    private static final int[] whiteArrs = new int[NUMBER_LENGTH_TEST];

    public static void main(String[] args) {

        //构造数组中数据
        for (int i = 0; i < NUMBER_LENGTH_TEST; i++) {
            whiteArrs[i] = RandomUtils.nextInt(0, 1000000000);
        }

        Arrays.sort(whiteArrs);
        System.out.println("whiteArrs length : " + whiteArrs.length);

        System.out.println();
        System.out.println("请输入数字：");

        try {

            InputStreamReader streamReader = new InputStreamReader(System.in);
            BufferedReader reader = new BufferedReader(streamReader);
            String result = reader.readLine();

            //转换
            if (!NumberUtils.isNumber(result)) {
                return;
            }
            int resultInt = Integer.valueOf(result);

            System.out.println();

            int response = rank(resultInt, whiteArrs);
            System.out.println("response : " + response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <pre>
     *
     *     只要endIndex 大于 startIndex 就循环
     *
     *     每次循环规则：
     *          数组劈一半
     *          如果key在前半截，缩短endIndex
     *          如果key在后半截，缩短startIndex
     * </pre>
     *
     * @param key
     * @param a   数组必须是有序的
     * @return
     */
    public static int rank(int key, int[] a) {

        //记录循环次数
        int count = 0;

        //数组起始下标
        int startIndex = 0;

        //数组最大长度
        int endIndex = a.length - 1;

        //被查找的键要么不存在，要么必然存在于a[startIndex...endIndex]之中
        while (startIndex <= endIndex) {

            //记录循环次数
            count++;

            //中间键
            int mid = startIndex + (endIndex - startIndex) / 2;

            System.out.println("第" + count + "次 ， startIndex:" + startIndex + " endIndex:" + endIndex + " mid:" + mid);

            if (key < a[mid]) {
                endIndex = mid - 1;
            } else if (key > a[mid]) {
                startIndex = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;

    }

}
