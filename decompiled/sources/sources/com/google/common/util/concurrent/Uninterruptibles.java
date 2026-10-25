package com.google.common.util.concurrent;

import java.util.concurrent.Future;

/* loaded from: classes5.dex */
public abstract class Uninterruptibles {
    public static <V> V getUninterruptibly(Future<V> r1) {
        boolean r0 = false;
    L12:
        V r12 = r1.get();     // Catch: Throwable -> L7 InterruptedException -> L11
    L4:
        if (r0 == false) goto L6;
        Thread.currentThread().interrupt();
    L6:
        return r12;
    L11:
        r0 = true;
    L7:
        th = move-exception;
        if (r0 == false) goto L10;
        Thread.currentThread().interrupt();
    L10:
        throw th;
    }
}
