package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;

/* loaded from: classes4.dex */
final /* synthetic */ class zzmy implements Supplier {
    private final /* synthetic */ zzmz zza;
    private final /* synthetic */ zzacr zzb;

    public /* synthetic */ zzmy(zzmz r1, zzacr r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.common.base.Supplier
    public final /* synthetic */ Object get() {
        return this.zza.zzb(this.zzb);
    }
}
