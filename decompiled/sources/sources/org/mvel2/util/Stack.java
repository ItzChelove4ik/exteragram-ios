package org.mvel2.util;

import java.io.Serializable;

/* loaded from: classes5.dex */
public interface Stack extends Serializable {
    void add(Object r1);

    void clear();

    void discard();

    boolean isEmpty();

    Object peek();

    Object peek2();

    Object pop();

    Object pop2();

    void push(Object r1);

    void push(Object r1, Object r2);

    void push(Object r1, Object r2, Object r3);

    Object pushAndPeek(Object r1);

    void showStack();

    int size();
}
