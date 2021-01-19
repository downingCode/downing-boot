package com.downing.boot.leetcode.zeroEvenOdd;

/**
 * @author downing
 * @desc
 * @date 2021/1/9 17:23
 */
public class LetCodeMainTest {

    public static void main(String[] args) {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(7);

        Thread t1 = new Thread(new ZeroThread(zeroEvenOdd));
        Thread t2 = new Thread(new EvenThread(zeroEvenOdd));
        Thread t3 = new Thread(new OddThread(zeroEvenOdd));
        t1.start();
        t2.start();
        t3.start();
    }
}
