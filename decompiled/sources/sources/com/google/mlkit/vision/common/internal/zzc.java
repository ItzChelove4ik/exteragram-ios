package com.google.mlkit.vision.common.internal;

import com.google.android.gms.tasks.OnFailureListener;

/* loaded from: classes5.dex */
public final /* synthetic */ class zzc implements OnFailureListener {
    public static final /* synthetic */ zzc zza = null;

    static {
        zza = new zzc();
    }

    private /* synthetic */ zzc() {
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception r1) {
        MobileVisionBase.zzc(r1);
    }
}
