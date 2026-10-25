package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
public abstract class PendingResultUtil {
    private static final zav zaa = null;

    public interface ResultConverter<R extends Result, T> {
        T convert(R r1);
    }

    static {
        zaa = new zar();
    }

    public static <R extends Result, T> Task<T> toTask(PendingResult<R> r3, ResultConverter<R, T> r4) {
        zav r0 = zaa;
        TaskCompletionSource r1 = new TaskCompletionSource();
        r3.addStatusListener(new zas(r3, r1, r4, r0));
        return r1.getTask();
    }

    public static <R extends Result> Task<Void> toVoidTask(PendingResult<R> r1) {
        return toTask(r1, new zau());
    }
}
