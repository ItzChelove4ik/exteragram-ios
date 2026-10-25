package org.mvel2.compiler;

/* loaded from: classes5.dex */
public class EndWithValue extends RuntimeException {
    private Object value;

    public EndWithValue(Object r1) {
        this.value = r1;
    }

    public Object getValue() {
        return this.value;
    }
}
