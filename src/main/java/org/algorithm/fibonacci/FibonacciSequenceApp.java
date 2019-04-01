package org.algorithm.fibonacci;

/**
 * 斐波那契数列
 *
 * @author CYX
 * @create 2019-03-30-23:06
 */
public class FibonacciSequenceApp {

    public static void main(String[] args) {

        for (int i = 1; i <= 45; i++) {

            System.out.println("feibonaci2 i=" + i + ",vaule=" + feibonaci2(i));

        }
    }

    /**
     * 斐波那契数列 递归实现
     *
     * @param n
     * @return
     */
    public static int feibonaci1(int n) {

        return 1;
    }

    /**
     * 斐波那契数列 非递归写法
     *
     * @param n
     * @return
     */
    public static int feibonaci2(int n) {

        int arr[] = new int[n + 1];
        arr[0] = 0;
        arr[1] = 1;

        for (int i = 1; i <= n; i++) {
            int num1 = arr[i - 1];
            int num2 = arr[i - 2];

            int num3 = num1 + num2;

            arr[i] = num3;
        }

        return arr[n];
    }

}
