package com.google.android.recaptcha.internal;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.Job;

/* loaded from: classes5.dex */
final /* synthetic */ class zzib implements OnCompleteListener {
    private final /* synthetic */ CompletableDeferred zza;

    public /* synthetic */ zzib(CompletableDeferred r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final /* synthetic */ void onComplete(Task r2) {
        CompletableDeferred r1 = this.zza;
        Exception r0 = r2.getException();
        if (r0 == null) goto L5;
        r1.completeExceptionally(r0);
        return;
    L5:
        if (r2.isCanceled() == false) goto L8;
        Job.cancel$default(r1, null, 1, null);
        return;
    L8:
        r1.complete(r2.getResult());
    }
}
