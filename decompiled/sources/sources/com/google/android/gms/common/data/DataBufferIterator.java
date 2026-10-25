package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: classes4.dex */
public class DataBufferIterator<T> implements Iterator<T> {
    protected final DataBuffer zaa;
    protected int zab;

    public DataBufferIterator(DataBuffer r1) {
        this.zaa = (DataBuffer) Preconditions.checkNotNull(r1);
        this.zab = -1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        DataBuffer r0 = this.zaa;
        if (this.zab >= (r0.getCount() - 1)) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Iterator
    public Object next() {
        if (hasNext() == false) goto L6;
        DataBuffer r0 = this.zaa;
        int r1 = this.zab + 1;
        this.zab = r1;
        return r0.get(r1);
    L6:
        int r3 = this.zab;
        StringBuilder r2 = new StringBuilder(String.valueOf(r3).length() + 35);
        r2.append("Cannot advance the iterator beyond ");
        r2.append(r3);
        throw new NoSuchElementException(r2.toString());
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
    }
}
