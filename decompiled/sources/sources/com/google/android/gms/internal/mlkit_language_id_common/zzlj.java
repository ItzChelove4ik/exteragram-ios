package com.google.android.gms.internal.mlkit_language_id_common;

import com.google.android.gms.tasks.OnFailureListener;

/* loaded from: classes4.dex */
public final /* synthetic */ class zzlj implements OnFailureListener {
    public final /* synthetic */ zzlk zza;
    public final /* synthetic */ long zzb;

    public /* synthetic */ zzlj(zzlk r1, long r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception r4) {
        this.zza.zzb(this.zzb, r4);
    }
}
