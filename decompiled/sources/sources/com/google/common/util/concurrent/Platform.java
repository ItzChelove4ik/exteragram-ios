package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
abstract class Platform {
    public static <V> V get(AbstractFuture<V> r0) {
        return r0.blockingGet();
    }

    public static <V> V get(AbstractFuture<V> r0, long r1, TimeUnit r3) {
        return r0.blockingGet(r1, r3);
    }

    public static void interruptCurrentThread() {
        Thread.currentThread().interrupt();
    }

    public static boolean isInstanceOfThrowableClass(Throwable r0, Class<? extends Throwable> r1) {
        return r1.isInstance(r0);
    }

    public static void restoreInterruptIfIsInterruptedException(Throwable r0) {
        Preconditions.checkNotNull(r0);
        if ((r0 instanceof InterruptedException) == false) goto L6;
        Thread.currentThread().interrupt();
        return;
    }

    public static void rethrowIfErrorOtherThanStackOverflow(Throwable r1) {
        Preconditions.checkNotNull(r1);
        if ((r1 instanceof Error) == true) goto L5;
        return;
    L5:
        if ((r1 instanceof StackOverflowError) == false) goto L8;
        return;
    L8:
        throw ((Error) r1);
    }
}
