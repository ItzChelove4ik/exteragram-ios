package com.google.android.gms.internal.measurement;

import com.google.android.apps.common.proguard.SideEffectFree;
import com.google.common.base.Preconditions;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* loaded from: classes4.dex */
public final class zzpo {
    private final AtomicReferenceArray zza;
    private final zzog zzb;

    public zzpo(zzog r1, int r2) {
        this.zzb = r1;
        this.zza = new AtomicReferenceArray(r2);
    }

    @SideEffectFree
    public final zzom zza(int r3, String r4, boolean r5) {
        AtomicReferenceArray r0 = this.zza;
        zzom r1 = (zzom) r0.get(r3);
        if (r1 != null) goto L8;
        zzom r2 = this.zzb.zza(r4, r5);
        if (zzpo$$ExternalSyntheticBackportWithForwarding0.m(r0, r3, null, r2) == false) goto L7;
        return r2;
    L7:
        return (zzom) Preconditions.checkNotNull((zzom) r0.get(r3));
    L8:
        return r1;
    }

    @SideEffectFree
    public final zzom zzb(int r3, String r4, long r5) {
        AtomicReferenceArray r0 = this.zza;
        zzom r1 = (zzom) r0.get(r3);
        if (r1 != null) goto L8;
        zzom r2 = this.zzb.zzb(r4, r5);
        if (zzpo$$ExternalSyntheticBackportWithForwarding0.m(r0, r3, null, r2) == false) goto L7;
        return r2;
    L7:
        return (zzom) Preconditions.checkNotNull((zzom) r0.get(r3));
    L8:
        return r1;
    }

    @SideEffectFree
    public final zzom zzc(int r3, String r4, double r5) {
        AtomicReferenceArray r32 = this.zza;
        zzom r52 = (zzom) r32.get(2);
        if (r52 != null) goto L8;
        zzom r2 = this.zzb.zzc("measurement.test.double_flag", -3.0d);
        if (zzpo$$ExternalSyntheticBackportWithForwarding0.m(r32, 2, null, r2) == false) goto L7;
        return r2;
    L7:
        return (zzom) Preconditions.checkNotNull((zzom) r32.get(2));
    L8:
        return r52;
    }

    @SideEffectFree
    public final zzom zzd(int r3, String r4, String r5) {
        AtomicReferenceArray r0 = this.zza;
        zzom r1 = (zzom) r0.get(r3);
        if (r1 != null) goto L8;
        zzom r2 = this.zzb.zzd(r4, r5);
        if (zzpo$$ExternalSyntheticBackportWithForwarding0.m(r0, r3, null, r2) == false) goto L7;
        return r2;
    L7:
        return (zzom) Preconditions.checkNotNull((zzom) r0.get(r3));
    L8:
        return r1;
    }
}
