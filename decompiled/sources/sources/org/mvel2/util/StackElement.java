package org.mvel2.util;

import java.io.Serializable;

/* loaded from: classes5.dex */
public class StackElement implements Serializable {
    public StackElement next;
    public Object value;

    public StackElement(StackElement r1, Object r2) {
        this.next = r1;
        this.value = r2;
    }
}
