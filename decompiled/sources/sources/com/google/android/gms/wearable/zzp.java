package com.google.android.gms.wearable;

import com.google.android.gms.wearable.internal.zzev;
import com.google.android.gms.wearable.internal.zzfx;

/* loaded from: classes5.dex */
public final /* synthetic */ class zzp implements Runnable {
    public final /* synthetic */ zzaa zza;
    public final /* synthetic */ zzfx zzb;
    public final /* synthetic */ zzev zzc;

    public /* synthetic */ zzp(zzaa r1, zzfx r2, zzev r3, byte[] r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzk(this.zzb, this.zzc);
    }
}
