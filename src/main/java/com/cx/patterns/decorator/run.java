package com.cx.patterns.decorator;

public class run {
    public static void main(String[] args) {
        new MiPhone(new AndroidPhone()).sendMsg("hello");
    }
}
