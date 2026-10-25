package com.google.android.gms.common.data;

import com.google.android.gms.common.api.Releasable;
import java.io.Closeable;

/* loaded from: classes4.dex */
public interface DataBuffer<T> extends Iterable<T>, Releasable, Closeable {
    T get(int r1);

    int getCount();
}
