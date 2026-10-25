package com.google.android.gms.internal.cast;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes4.dex */
public final class zzgi implements Result {
    private final Status zza;
    private final zzgc zzb;

    public zzgi(Status r1, zzgc r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zza;
    }

    public final String toString() {
        zzgc r1 = this.zzb;
        Preconditions.checkNotNull(r1);
        boolean r0 = true;
        if (r1.zza() == 1) goto L7;
        r0 = false;
    L7:
        return String.format("OptInOptionsResultImpl[%s]", new Object[]{Boolean.valueOf(r0)});
    }

    public final boolean zza() {
        zzgc r1 = this.zzb;
        Preconditions.checkNotNull(r1);
        if (r1.zza() != 1) goto L5;
        return true;
    L5:
        return false;
    }
}
