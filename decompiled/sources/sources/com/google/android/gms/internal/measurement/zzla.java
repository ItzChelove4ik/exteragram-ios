package com.google.android.gms.internal.measurement;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
final /* synthetic */ class zzla implements OnCompleteListener {
    private final /* synthetic */ zzkz zza;

    public /* synthetic */ zzla(zzkz r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final /* synthetic */ void onComplete(Task r2) {
        zzkz r1 = this.zza;
        if (r2.isCanceled() == false) goto L7;
        r1.cancel(false);
        return;
    L7:
        if (r2.isSuccessful() == false) goto L10;
        r1.set(r2.getResult());
        return;
    L10:
        Exception r22 = r2.getException();
        if (r22 == null) goto L14;
        r1.setException(r22);
        return;
    L14:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
    }
}
