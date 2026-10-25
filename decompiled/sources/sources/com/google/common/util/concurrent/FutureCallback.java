package com.google.common.util.concurrent;

/* loaded from: classes5.dex */
public interface FutureCallback<V> {
    void onFailure(Throwable r1);

    void onSuccess(V r1);
}
