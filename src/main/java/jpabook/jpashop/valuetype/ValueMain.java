package jpabook.jpashop.valuetype;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValueMain {

    public static void main(String[] args) {
    }

    private static void primitiveType() {
        int a = 10;
        int b = a;

        b = 20;

        // 기본값 타입은 값을 공유하는게 아닌 복사를 한다.
        System.out.println("a = " + a); // 10 출력
        System.out.println("b = " + b); // 20 출력
    }
}
