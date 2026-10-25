package com.google.android.gms.internal.clearcut;

import com.google.android.gms.common.api.Status;

/* loaded from: classes4.dex */
final class zzi extends zzg {
    private final /* synthetic */ zzh zzap;

    public zzi(zzh r1) {
        this.zzap = r1;
        super(null);
    }

    @Override // com.google.android.gms.internal.clearcut.zzl
    public final void zza(Status r1) {
        this.zzap.setResult(r1);
    }
}
