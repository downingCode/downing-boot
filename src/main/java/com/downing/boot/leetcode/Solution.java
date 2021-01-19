package com.downing.boot.leetcode;

class Solution {
    public boolean isPalindrome(int x) {
        String str = x+ "";
        StringBuilder s = new StringBuilder();
        for(int i= str.length();i > 0;i--){
            char c = str.charAt(i);
            s.append(c);
        }
        return str.equals(s.toString());
    }
}