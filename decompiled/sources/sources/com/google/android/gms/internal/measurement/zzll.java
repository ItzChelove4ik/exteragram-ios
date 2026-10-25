package com.google.android.gms.internal.measurement;

import android.content.Context;
import com.google.common.base.Supplier;

/* loaded from: classes4.dex */
final /* synthetic */ class zzll implements Supplier {
    private final /* synthetic */ Context zza;

    public /* synthetic */ zzll(Context r1) {
        this.zza = r1;
    }

    @Override // com.google.common.base.Supplier
    public final /* synthetic */ Object get() {
        int r0 = zzlk.$r8$clinit;
        zzlj r02 = new zzlj(null);
        r02.zza(this.zza);
        return r02.zzb();
    }
}
