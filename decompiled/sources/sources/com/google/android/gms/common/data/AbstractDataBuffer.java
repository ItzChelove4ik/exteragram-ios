package com.google.android.gms.common.data;

import java.util.Iterator;

/* loaded from: classes4.dex */
public abstract class AbstractDataBuffer<T> implements DataBuffer<T> {
    protected final DataHolder mDataHolder;

    public AbstractDataBuffer(DataHolder r1) {
        this.mDataHolder = r1;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        release();
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        return new DataBufferIterator(this);
    }

    @Override // com.google.android.gms.common.api.Releasable
    public void release() {
        DataHolder r0 = this.mDataHolder;
        if (r0 == null) goto L6;
        r0.close();
        return;
    }
}
