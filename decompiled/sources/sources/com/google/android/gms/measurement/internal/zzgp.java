package com.google.android.gms.measurement.internal;

import com.google.android.gms.tasks.OnFailureListener;

/* loaded from: classes5.dex */
final /* synthetic */ class zzgp implements OnFailureListener {
    private final /* synthetic */ zzgq zza;
    private final /* synthetic */ long zzb;

    public /* synthetic */ zzgp(zzgq r1, long r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final /* synthetic */ void onFailure(Exception r4) {
        this.zza.zzc(this.zzb, r4);
    }
}
