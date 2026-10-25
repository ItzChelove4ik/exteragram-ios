package com.google.android.gms.internal.cast;

import com.google.android.gms.tasks.OnSuccessListener;

/* loaded from: classes4.dex */
final /* synthetic */ class zzh implements OnSuccessListener {
    private final /* synthetic */ zzj zza;
    private final /* synthetic */ zzqr zzb;
    private final /* synthetic */ int zzc;

    public /* synthetic */ zzh(zzj r1, zzqr r2, int r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final /* synthetic */ void onSuccess(Object r3) {
        this.zza.zzf(this.zzb, this.zzc, (Boolean) r3);
    }
}
