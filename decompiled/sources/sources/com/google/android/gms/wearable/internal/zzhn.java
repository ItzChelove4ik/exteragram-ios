package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

/* loaded from: classes5.dex */
abstract class zzhn extends zza {
    private BaseImplementation$ResultHolder zza;

    public zzhn(BaseImplementation$ResultHolder r1) {
        this.zza = r1;
    }

    public final void zzI(Object r2) {
        BaseImplementation$ResultHolder r0 = this.zza;
        if (r0 == null) goto L6;
        r0.setResult(r2);
        this.zza = null;
        return;
    }
}
