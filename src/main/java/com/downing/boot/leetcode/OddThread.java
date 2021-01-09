package com.downing.boot.leetcode;

/**
 * @author downing
 * @desc
 * @date 2021/1/9 17:38
 */
public class OddThread implements Runnable{

    private ZeroEvenOdd zeroEvenOdd;

    public OddThread(ZeroEvenOdd zeroEvenOdd) {
        this.zeroEvenOdd = zeroEvenOdd;
    }

    @Override
    public void run() {
        try {
            zeroEvenOdd.odd(System.out::print);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
