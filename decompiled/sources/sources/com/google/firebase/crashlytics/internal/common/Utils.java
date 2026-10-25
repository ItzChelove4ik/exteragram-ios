package com.google.firebase.crashlytics.internal.common;

import android.os.Looper;
import com.google.android.gms.tasks.Task;
import com.google.protobuf.UnsafeUtil$$ExternalSyntheticBUOutline0;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes5.dex */
public abstract class Utils {
    private static final ExecutorService TASK_CONTINUATION_EXECUTOR_SERVICE = null;

    /* renamed from: $r8$lambda$4ODRW-4YtgOypI4EnuCu0xhdi2o, reason: not valid java name */
    public static /* synthetic */ Object m1941$r8$lambda$4ODRW4YtgOypI4EnuCu0xhdi2o(CountDownLatch r0, Task r1) {
        r0.countDown();
        return null;
    }

    static {
        TASK_CONTINUATION_EXECUTOR_SERVICE = ExecutorUtils.buildSingleThreadExecutorService("awaitEvenIfOnMainThread task continuation executor");
    }

    @Deprecated
    public static <T> T awaitEvenIfOnMainThread(Task<T> r4) {
        CountDownLatch r0 = new CountDownLatch(1);
        r4.continueWith(TASK_CONTINUATION_EXECUTOR_SERVICE, new Utils$$ExternalSyntheticLambda0(r0));
        Looper r1 = Looper.getMainLooper();
        Looper r2 = Looper.myLooper();
        TimeUnit r3 = TimeUnit.MILLISECONDS;
        if (r1 != r2) goto L5;
        r0.await(3000, r3);
    L7:
        if (r4.isSuccessful() == false) goto L11;
        return r4.getResult();
    L11:
        if (r4.isCanceled() == true) goto L19;
        if (r4.isComplete() == false) goto L17;
        UnsafeUtil$$ExternalSyntheticBUOutline0.m(r4.getException());
        return null;
    L17:
        throw new TimeoutException();
    L19:
        throw new CancellationException("Task is already canceled");
    L5:
        r0.await(4000, r3);
        goto L7
    }

    public static boolean awaitUninterruptibly(CountDownLatch r3, long r4, TimeUnit r6) {
        boolean r0 = false;
        long r42 = r6.toNanos(r4);     // Catch: Throwable -> L9
        long r1 = System.nanoTime() + r42;
    L17:
        boolean r32 = r3.await(r42, TimeUnit.NANOSECONDS);     // Catch: Throwable -> L9 InterruptedException -> L11
    L6:
        if (r0 == false) goto L8;
        Thread.currentThread().interrupt();
    L8:
        return r32;
    L11:
        r0 = true;
        r42 = r1 - System.nanoTime();
    L9:
        th = move-exception;
        if (r0 == false) goto L16;
        Thread.currentThread().interrupt();
    L16:
        throw th;
    }
}
