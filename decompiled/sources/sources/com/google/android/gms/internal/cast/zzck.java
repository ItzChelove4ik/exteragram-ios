package com.google.android.gms.internal.cast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zzck implements OnFailureListener {
    private final /* synthetic */ TaskCompletionSource zza;

    public /* synthetic */ zzck(TaskCompletionSource r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final /* synthetic */ void onFailure(Exception r1) {
        zzcn.zzb(this.zza, r1);
    }
}
