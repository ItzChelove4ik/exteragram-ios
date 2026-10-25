package com.google.android.gms.internal.cast;

import com.google.android.gms.tasks.OnFailureListener;

/* loaded from: classes4.dex */
final /* synthetic */ class zzcc implements OnFailureListener {
    private final /* synthetic */ zzce zza;

    public /* synthetic */ zzcc(zzce r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final /* synthetic */ void onFailure(Exception r1) {
        this.zza.zzk(r1);
    }
}
