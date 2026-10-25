package com.google.android.gms.cast.framework;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zzc implements OnFailureListener {
    private final /* synthetic */ TaskCompletionSource zza;

    public /* synthetic */ zzc(TaskCompletionSource r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final /* synthetic */ void onFailure(Exception r2) {
        int r0 = CastButtonFactory.zza;
        this.zza.setException(r2);
    }
}
