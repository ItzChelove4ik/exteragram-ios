package com.google.android.gms.internal.clearcut;

import android.database.ContentObserver;
import android.os.Handler;

/* loaded from: classes4.dex */
final class zzac extends ContentObserver {
    private final /* synthetic */ zzab zzdm;

    public zzac(zzab r1, Handler r2) {
        this.zzdm = r1;
        super(null);
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean r1) {
        this.zzdm.zzh();
        zzab.zza(this.zzdm);
    }
}
