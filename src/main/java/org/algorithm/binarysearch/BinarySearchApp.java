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

    private static final int NUMBER_LENGTH_TEST = 11;

    private static final int[] WHITE_ARRS = new int[NUMBER_LENGTH_TEST];

    public static void main(String[] args) {

        //构造数据
        constructionData();

        try {

            //入参处理
            System.out.println("请输入数字：");
            InputStreamReader streamReader = new InputStreamReader(System.in);
            BufferedReader reader = new BufferedReader(streamReader);
            String result = reader.readLine();
            if (!NumberUtils.isCreatable(result)) {
                System.out.println("请输入有效数字!");
                return;
            }
            int resultInt = Integer.valueOf(result);

            System.out.println();

            //process
            int index = rank(resultInt, WHITE_ARRS);
            System.out.println("index : " + index);

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
        int tempCount = 0;

        //数组起始下标
        int startIndex = 0;

        //数组结束长度(下标)
        int endIndex = a.length - 1;

        //被查找的键要么不存在，要么必然存在于a[startIndex...endIndex]之中
        while (startIndex <= endIndex) {

            //记录循环次数
            tempCount++;

            //中间键值下标
            //扫描左侧的子序列，startIndex一直为0，endIndex改变，这样min向左侧移动。
            //扫描右侧的子序列，stratIndex改变，endIndex改变，这样min向右移动。
            int mid = startIndex + (endIndex - startIndex) / 2;

            //中间键值下标对应的数字
            int midValue = a[mid];

            System.out.println("第" + tempCount + "次 ， startIndex:" + startIndex + " endIndex:" + endIndex + " mid:" + mid + " midValue:" + midValue);

            //中间键值 大于 key，修改endIndex，下次扫描左侧子序列
            if (key < midValue) {
                endIndex = mid - 1;
            } else if (key > a[mid]) {
                startIndex = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;

    }

    /**
     * 构造数据
     */
    public static void constructionData() {

        //构造数组中数据 可能有重复数据，无所谓了...
        for (int i = 0; i < NUMBER_LENGTH_TEST; i++) {
            WHITE_ARRS[i] = RandomUtils.nextInt(0, 10000);
        }
        //排序
        Arrays.sort(WHITE_ARRS);
        System.out.println(Arrays.toString(WHITE_ARRS));
    }
}
