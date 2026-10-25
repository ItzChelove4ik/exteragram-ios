package org.mvel2.templates.util;

import java.util.Iterator;

/* loaded from: classes5.dex */
public class CountIterator implements Iterator {
    int countTo;
    int cursor;

    public CountIterator(int r1) {
        this.countTo = r1;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.cursor >= this.countTo) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Iterator
    public Object next() {
        int r0 = this.cursor;
        this.cursor = r0 + 1;
        return Integer.valueOf(r0);
    }

    @Override // java.util.Iterator
    public void remove() {
    }
}
