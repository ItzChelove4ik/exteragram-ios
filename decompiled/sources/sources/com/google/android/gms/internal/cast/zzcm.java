package com.google.android.gms.internal.cast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zzcm implements OnSuccessListener {
    private final /* synthetic */ TaskCompletionSource zza;

    public /* synthetic */ zzcm(TaskCompletionSource r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final /* synthetic */ void onSuccess(Object r2) {
        zzfv r22 = (zzfv) r2;
        int r0 = zzcn.$r8$clinit;
        boolean r02 = false;
        if (r22 != null) goto L5;
    L7:
        this.zza.trySetResult(Boolean.valueOf(r02));
        return;
    L5:
        if (r22.zza() == false) goto L7;
        r02 = true;
        goto L7
    }
}
