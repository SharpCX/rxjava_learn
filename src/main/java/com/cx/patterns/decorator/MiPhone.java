package com.cx.patterns.decorator;

public class MiPhone implements DecoratorPhone{
    DecoratorPhone phone;

    public MiPhone(DecoratorPhone phone){
        this.phone = phone;
    }

    @Override
    public void sendMsg(String msg) {
        System.out.println("mi phone filter msg");
        this.phone.sendMsg(msg);
    }
}
