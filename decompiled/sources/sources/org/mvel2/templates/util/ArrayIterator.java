package org.mvel2.templates.util;

import java.util.Iterator;

/* loaded from: classes5.dex */
public class ArrayIterator implements Iterator {
    private Object[] array;
    private int cursor;

    public ArrayIterator(Object[] r2) {
        this.cursor = 0;
        this.array = r2;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.cursor == this.array.length) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Iterator
    public Object next() {
        Object[] r0 = this.array;
        int r1 = this.cursor;
        this.cursor = r1 + 1;
        return r0[r1];
    }

    @Override // java.util.Iterator
    public void remove() {
    }
}
