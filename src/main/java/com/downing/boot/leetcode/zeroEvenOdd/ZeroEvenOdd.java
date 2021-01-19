package com.downing.boot.leetcode.zeroEvenOdd;

import java.util.function.IntConsumer;

class ZeroEvenOdd{
    private int n;

    private volatile int flag = 0;
    private volatile int x = 1;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        while(x<=n){
            if(flag==0){
                printNumber.accept(0);
                if(x%2 == 0){
                    flag = 1;
                }else{
                    flag = 2;
                }
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while(x <= n){
            if(flag == 1){
                printNumber.accept(x++);
                flag = 0;
            }
        }

    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while(x <= n){
            if(flag == 2){
                printNumber.accept(x++);
                flag = 0;
            }
        }
    }
}