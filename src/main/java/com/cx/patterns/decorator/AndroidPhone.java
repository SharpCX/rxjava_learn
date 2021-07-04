package com.cx.patterns.decorator;

public class AndroidPhone implements DecoratorPhone{
    @Override
    public void sendMsg(String msg) {
        System.out.println("send msg:"+msg);
    }
}
