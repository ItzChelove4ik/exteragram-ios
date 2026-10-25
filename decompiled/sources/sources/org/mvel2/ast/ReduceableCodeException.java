package org.mvel2.ast;

/* loaded from: classes5.dex */
public class ReduceableCodeException extends RuntimeException {
    private Object literal;

    public ReduceableCodeException(Object r1) {
        this.literal = r1;
    }

    public Object getLiteral() {
        return this.literal;
    }
}
