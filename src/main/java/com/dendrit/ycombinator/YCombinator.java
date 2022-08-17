package com.dendrit.ycombinator;

import java.util.function.Function;

interface Fix<T, R> extends Function<Fix<T, R>, Function<T, R>> {

}

public class YCombinator {

    public static void main(String[] args) {
        Function<Function<Integer, Integer>, Function<Integer, Integer>> ctor = self -> n -> {
            if (n == 0) {
                return 1;
            } else {
                return n * self.apply(n - 1);
            }
        };
        Function<Integer, Integer> factorial = Y(ctor);
        System.out.println(factorial.apply(10));
    }

    public static <T, R> Function<T, R> Y(Function<Function<T, R>, Function<T, R>> ctor) {
        Fix<T, R> fix = g -> g.apply(g);
        return fix.apply(g -> ctor.apply(n -> g.apply(g).apply(n)));
    }

}
