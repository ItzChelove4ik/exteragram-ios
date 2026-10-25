package com.google.android.gms.internal.measurement;

import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.cast.internal.zzar$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public abstract class zzabh {
    private final int zza;
    private final zzza zzb;

    public zzabh(zzza r2, int r3) {
        if (r2 == null) goto L9;
        if (r3 < 0) goto L7;
        this.zza = r3;
        this.zzb = r2;
        return;
    L7:
        zzar$$ExternalSyntheticBUOutline1.m(String.valueOf(r3).length() + 15, "invalid index: ", r3);
        throw null;
    L9:
        f$$ExternalSyntheticBUOutline1.m("format options cannot be null");
        throw null;
    }

    public abstract void zzb(zzabi r1, Object r2);

    public final int zzc() {
        return this.zza;
    }

    public final zzza zzd() {
        return this.zzb;
    }

    public final void zze(zzabi r3, Object[] r4) {
        int r0 = this.zza;
        if (r0 >= r4.length) goto L10;
        Object r42 = r4[r0];
        if (r42 == null) goto L8;
        zzb(r3, r42);
        return;
    L8:
        r3.zzf();
        return;
    L10:
        r3.zze();
    }
}
