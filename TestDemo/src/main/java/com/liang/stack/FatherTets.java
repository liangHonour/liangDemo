package com.liang.stack;

public class FatherTets {
    public void fatherOne(){
        Main main = new Main();
        main.MainTest();
    }
    public void fatherTwo(){
        this.fatherOne();
    }

    public void fatherThree(){
        this.fatherTwo();
    }

}
