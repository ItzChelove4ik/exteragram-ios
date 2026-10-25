package com.google.android.datatransport.runtime.retries;

/* loaded from: classes4.dex */
public abstract class Retries {
    public static <TInput, TResult, TException extends Throwable> TResult retry(int r2, TInput r3, Function<TInput, TResult, TException> r4, RetryStrategy<TInput, TResult> r5) {
        if (r2 < 1) goto L5;
    L6:
        TResult r1 = r4.apply(r3);
        r3 = r5.shouldRetry(r3, r1);
        if (r3 == null) goto L10;
        r2 = r2 - 1;
        if (r2 >= 1) goto L6;
    L10:
        return r1;
    L5:
        return r4.apply(r3);
    }
}
