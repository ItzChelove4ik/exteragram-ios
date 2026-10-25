package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
final class zas implements PendingResult.StatusListener {
    final /* synthetic */ PendingResult zaa;
    final /* synthetic */ TaskCompletionSource zab;
    final /* synthetic */ PendingResultUtil.ResultConverter zac;
    final /* synthetic */ zav zad;

    public zas(PendingResult r1, TaskCompletionSource r2, PendingResultUtil.ResultConverter r3, zav r4) {
        this.zaa = r1;
        this.zab = r2;
        this.zac = r3;
        this.zad = r4;
    }

    @Override // com.google.android.gms.common.api.PendingResult.StatusListener
    public final void onComplete(Status r4) {
        if (r4.isSuccess() == false) goto L6;
        Result r42 = this.zaa.await(0, TimeUnit.MILLISECONDS);
        this.zab.setResult(this.zac.convert(r42));
        return;
    L6:
        this.zab.setException(ApiExceptionUtil.fromStatus(r4));
    }
}
