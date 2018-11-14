package com.yf.common.utils;

import java.util.Arrays;

/**
 * @Package com.yf.common.utils
 * @Description: 插入排序  使用于 近乎于 有序的数组 o(N)
 * @author: jingh
 * @date 2018/11/14 21:42
 */
public class insertionSort {
    private insertionSort() {
    }

    public static void sort(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Comparable comparable = arr[i];
//            从已排序的数组   寻找 合适的插入位置
//            遍历到最后一个位置为 j和 j-1 比较 最多考虑到 j=1的情况 因此j>0（当前位置和前一个位置比较 如果比他小 则交换位置）
            for (int j = i; j > 0; j--) {
                if (arr[j - 1].compareTo(arr[j]) > 0) {
                    swap(arr,j,j-1);
                }else{
                    break;
                }
            }
        }
    }

    /**
     * 插入排序 优化
     * 优化 多次交换的无效性
     * @param arr
     */
    public static void optimizationSort(Comparable [] arr){

        for(int i=1;i<arr.length;i++){
//            临时缓存 当前待插入的数
//            Comparable c = arr[i];
//            int j=i;
//            for (;j>0 ;j--){
//                // 如果 前面的大小 大于后面的则 将 前一位往后挪一位
//                if(arr[j-1].compareTo(c) > 0){
//                    arr[j] = arr[j-1];
//                }else{
//                    arr[j] = c;
//                    break;
//                }
//            }


            // 写法2
//            for( int j = i; j > 0 && arr[j].compareTo(arr[j-1]) < 0 ; j--) {
//                swap(arr, j, j-1);
//            }
//

            // 写法3
            Comparable e = arr[i];
            int j = i;
            for( ; j > 0 && arr[j-1].compareTo(e) > 0 ; j--){
                arr[j] = arr[j-1];
            }
            arr[j] = e;
        }
    }

    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

//     测试InsertionSort
    public static void main(String[] args) {

        int N = 10000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 234131);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        SortTestHelper.testSort("com.yf.common.utils.insertionSort", "optimizationSort",arr);
        SortTestHelper.testSort("com.yf.common.utils.insertionSort", "sort",arr2);
    }
}
