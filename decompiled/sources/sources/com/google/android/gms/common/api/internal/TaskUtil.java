package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.ApiMetadata;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
public abstract class TaskUtil {
    public static void setResultOrApiException(Status r1, TaskCompletionSource<Void> r2) {
        setResultOrApiException(r1, null, r2);
    }

    public static void setResultOrApiException(Status r0, TaskCompletionSource<Void> r1, ApiMetadata r2) {
        setResultOrApiException(r0, r1);
    }

    public static <ResultT> void setResultOrApiException(Status r1, ResultT r2, TaskCompletionSource<ResultT> r3) {
        if (r1.isSuccess() == false) goto L6;
        r3.setResult(r2);
        return;
    L6:
        r3.setException(ApiExceptionUtil.fromStatus(r1));
    }

    public static <ResultT> void setResultOrApiException(Status r0, ResultT r1, TaskCompletionSource<ResultT> r2, ApiMetadata r3) {
        setResultOrApiException(r0, r1, r2);
    }

    public static <ResultT> boolean trySetResultOrApiException(Status r1, ResultT r2, TaskCompletionSource<ResultT> r3) {
        if (r1.isSuccess() == false) goto L7;
        return r3.trySetResult(r2);
    L7:
        return r3.trySetException(ApiExceptionUtil.fromStatus(r1));
    }
}
