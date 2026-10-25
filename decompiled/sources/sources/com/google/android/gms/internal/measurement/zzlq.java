package com.google.android.gms.internal.measurement;

import com.google.common.base.Optional;
import com.google.common.base.Supplier;

/* loaded from: classes4.dex */
final /* synthetic */ class zzlq implements Supplier {
    private final /* synthetic */ Supplier zza;

    public /* synthetic */ zzlq(Supplier r1) {
        this.zza = r1;
    }

    @Override // com.google.common.base.Supplier
    public final /* synthetic */ Object get() {
        int r0 = zzlk.$r8$clinit;
        return (zzqm) ((Optional) this.zza.get()).orNull();
    }
}
