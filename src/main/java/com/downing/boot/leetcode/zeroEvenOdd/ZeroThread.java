package com.downing.boot.leetcode.zeroEvenOdd;

/**
 * @author downing
 * @desc
 * @date 2021/1/9 17:38
 */
public class ZeroThread implements Runnable{

    private ZeroEvenOdd zeroEvenOdd;

    public ZeroThread(ZeroEvenOdd zeroEvenOdd) {
        this.zeroEvenOdd = zeroEvenOdd;
    }

    @Override
    public void run() {
        try {
            zeroEvenOdd.zero(System.out::print);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
