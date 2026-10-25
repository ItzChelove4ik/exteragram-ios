package com.google.android.recaptcha.internal;

import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import kotlinx.coroutines.Deferred;

/* loaded from: classes5.dex */
public abstract class zzhz {
    public static final Task zza(Deferred r2) {
        TaskCompletionSource r1 = new TaskCompletionSource(new CancellationTokenSource().getToken());
        r2.invokeOnCompletion(new zzhy(r1, r2));
        return r1.getTask();
    }
}
