package com.google.android.gms.internal.measurement;

import android.content.Context;
import com.google.common.base.Supplier;

/* loaded from: classes4.dex */
final /* synthetic */ class zzlv implements Supplier {
    private final /* synthetic */ Context zza;

    public /* synthetic */ zzlv(Context r1) {
        this.zza = r1;
    }

    @Override // com.google.common.base.Supplier
    public final /* synthetic */ Object get() {
        int r0 = zzlw.$r8$clinit;
        return zzlf.zza(this.zza);
    }
}
