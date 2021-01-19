package com.downing.boot.leetcode.zeroEvenOdd;

/**
 * @author downing
 * @desc
 * @date 2021/1/9 17:38
 */
public class EvenThread implements Runnable{

    private ZeroEvenOdd zeroEvenOdd;

    public EvenThread(ZeroEvenOdd zeroEvenOdd) {
        this.zeroEvenOdd = zeroEvenOdd;
    }

    @Override
    public void run() {
        try {
            zeroEvenOdd.even(System.out::print);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
