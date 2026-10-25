package com.google.android.gms.cast.framework;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zzf implements OnFailureListener {
    private final /* synthetic */ TaskCompletionSource zza;

    public /* synthetic */ zzf(TaskCompletionSource r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final /* synthetic */ void onFailure(Exception r1) {
        this.zza.setException(r1);
    }
}
