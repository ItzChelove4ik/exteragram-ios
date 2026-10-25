package com.google.android.gms.internal.measurement;

import com.google.common.base.Preconditions;
import java.util.UUID;

/* loaded from: classes4.dex */
abstract class zzvt extends zzvn {
    private final zzwl zza;

    public zzvt(String r1, zzws r2, zzwl r3, zzwq r4) {
        super(r1, r2, r4);
        Preconditions.checkArgument(r3.zze());
        this.zza = r3;
    }

    public zzvt(String r1, UUID r2, String r3, zzwl r4, zzwq r5) {
        super(r1, r2, r3, r5);
        Preconditions.checkArgument(r4.zze());
        this.zza = r4;
    }

    @Override // com.google.android.gms.internal.measurement.zzws
    public final zzwl zzh() {
        return zzwl.zza(this.zza, zzl());
    }
}
