package com.yf.common.utils.learn;


import java.math.BigInteger;
import java.util.Arrays;

/**
 * @Package 归并排序
 * @Description: TODO
 * @author: jingh
 * @date 2018/11/21 22:06
 */
public class MergeSort {

    public static void sort(Comparable [] arr){
        mergeSort(arr,0,arr.length-1);
    }

    /**
     * @param: 归并排序
     * @return: 
     * @auther: jingh
     * @date: 2018/11/21 22:10
     */
    public static void mergeSort(Comparable [] arr ,int l,int r){
        if(l>=r){
            return;
        }

        int mid = (l+r) / 2;
        mergeSort(arr,l,mid);
        mergeSort(arr,mid+1,r);
        merge(arr,l,mid,r);
    }

    public static void merge(Comparable [] arr,int l,int mid,int r){
        //新建一个辅助数组，范围为(l，r+1)
        Comparable[] aux = Arrays.copyOfRange( arr, l, r+1);

        //初始化，i指向左半部分的起始索引位置,j指向右半部分的起始位置mid+1
        int i=l;int j=mid +1;

        for (int k = l;k<=r;k++){
            //如果左半部分元素已经全部处理完毕
            if(i> mid){
                arr[k] = aux[j-l];
                j++;
            }else if(j >r){//如果右半部分元素已经全部处理完毕
                arr[k] = aux[i -l];
                i++;
            }else if(aux[i-l].compareTo(aux[j-l])<0){//左半部分的值小于右半部分的值
                arr[k] = aux[i-l];
                i++;
            }else{//右半部分的值小于左半部分的值
                arr[k] = aux[j-l];
                j++;
            }

        }

    }
}
