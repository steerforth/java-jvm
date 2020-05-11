package com.steer.jvm.overide;

public class Cat extends Animal {
    @Override
    void eat() {
//        super.eat();
        System.out.println("猫在吃东西");
    }
}
