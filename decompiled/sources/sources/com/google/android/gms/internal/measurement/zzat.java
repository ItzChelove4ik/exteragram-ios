package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzat implements zzao {
    public zzat() {
    }

    public final boolean equals(Object r1) {
        if (r1 != this) goto L6;
        return true;
    L6:
        return r1 instanceof zzat;
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final String zzc() {
        return "undefined";
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final zzao zzcG(String r1, zzg r2, List r3) {
        throw new IllegalStateException(String.format("Undefined has no function %s", new Object[]{r1}));
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final Double zzd() {
        return Double.valueOf(Double.NaN);
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final Boolean zze() {
        return Boolean.FALSE;
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final Iterator zzf() {
        return null;
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final zzao zzt() {
        return zzao.zzf;
    }
}
