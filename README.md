# algorithm

算法练习

    冒泡排序：org.algorithm.bubblesort

    选择排序：org.algorithm.selectsort

    斐波那契：org.algorithm.fibonacci

## 二分查找

    org.algorithm.binarysearch

    给定一个整数X，和整数队列A0,A1,...,A(N-1)
    后者已经预先排序并在内存中，求下标i使得A(i)=X，如果X不在数据队列中，则返回i=-1。

    明显的解法是从左到右扫描数据，其运行花费线性时间；这样子就没有用到该数据队列已经排序的事实

    一个好的策略是验证X是否是居中的元素。
    如果是，则找到了答案。
    如果X小于居中元素，那么可以应用同样的策略于居中左边已排序的子序列。
    如果X大于居中元素，那么检查数据的右半边数据队列。