package com.liang.swich;

import org.junit.Test;

public class Defult {
    public static void main(String[] args) {
        Integer a = 4;
        Integer b = 2;
        switch (a){
            case 4: a++;
            case 1: b++;
            case 2: b++;
        }
        System.out.println(a);
        System.out.println(b);
    }

    @Test
    public void intTest(){
        int i = 3;
        switch (i) {
            case 1:
                System.out.println(1);
                break;
            case 2:
                System.out.println(2);
                break;
            case 3:
                System.out.println(3);
                break;
            default:
                System.out.println(0);
        }

    }
}

