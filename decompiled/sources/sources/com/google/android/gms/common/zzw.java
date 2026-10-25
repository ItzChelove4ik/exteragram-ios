package com.google.android.gms.common;

import android.content.Context;
import com.google.android.gms.dynamic.ObjectWrapper;

/* loaded from: classes4.dex */
final class zzw {
    private final String zza;
    private final boolean zzb;
    private final boolean zzc;

    public /* synthetic */ zzw(String r1, boolean r2, boolean r3, boolean r4, boolean r5, boolean r6, byte[] r7) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r5;
    }

    public final boolean zza() {
        return this.zzc;
    }

    /* JADX WARN: Type inference failed for: r4v0, types: [android.os.IBinder, com.google.android.gms.dynamic.IObjectWrapper] */
    public final zzp zzb(Context r9) {
        ?? r4 = ObjectWrapper.wrap(r9);
        return new zzp(this.zza, this.zzb, false, r4, false, true, false);
    }
}
