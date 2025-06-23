package jpabook.jpashop;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValueMain {

    public static void main(String[] args) {
        int a = 10;
        int b = a;

        b = 20;

        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
}
