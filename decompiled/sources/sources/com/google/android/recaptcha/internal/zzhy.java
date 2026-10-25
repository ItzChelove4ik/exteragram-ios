package com.google.android.recaptcha.internal;

import com.google.android.gms.tasks.RuntimeExecutionException;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.CancellationException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.Deferred;

/* loaded from: classes5.dex */
final /* synthetic */ class zzhy implements Function1 {
    private final /* synthetic */ TaskCompletionSource zza;
    private final /* synthetic */ Deferred zzb;

    public /* synthetic */ zzhy(TaskCompletionSource r1, Deferred r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* synthetic */ Object invoke(Object r3) {
        Throwable r32 = (Throwable) r3;
        boolean r0 = r32 instanceof CancellationException;
        TaskCompletionSource r1 = this.zza;
        if (r0 == false) goto L5;
        r1.setException((Exception) r32);
    L16:
        return Unit.INSTANCE;
    L5:
        Deferred r2 = this.zzb;
        Throwable r33 = r2.getCompletionExceptionOrNull();
        if (r33 != null) goto L9;
        r1.setResult(r2.getCompleted());
        goto L16
    L9:
        if ((r33 instanceof Exception) == false) goto L11;
        Exception r22 = (Exception) r33;
    L12:
        if (r22 != null) goto L14;
        r22 = new RuntimeExecutionException(r33);
    L14:
        r1.setException(r22);
        goto L16
    L11:
        r22 = null;
        goto L12
    }
}
