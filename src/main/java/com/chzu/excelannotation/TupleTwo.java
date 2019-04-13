package com.chzu.excelannotation;

/**
 * 元组，2个属性
 * @param <A>
 * @param <B>
 */
public class TupleTwo<A, B> {

    private final A title;
    private final B attribute;

    public TupleTwo(A a, B b) {
        this.title = a;
        this.attribute = b;
    }

    public A getExcelTitle() {
        return this.title;
    }

    public B getPojoAttribute() {
        return this.attribute;
    }
}