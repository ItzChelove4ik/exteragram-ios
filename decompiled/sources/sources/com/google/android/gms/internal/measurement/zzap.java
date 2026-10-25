package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzap implements zzao {
    private final String zza;
    private final ArrayList zzb;

    public zzap(String r1, List r2) {
        this.zza = r1;
        ArrayList r12 = new ArrayList();
        this.zzb = r12;
        r12.addAll(r2);
    }

    public final boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if ((r4 instanceof zzap) == true) goto L8;
        return false;
    L8:
        zzap r42 = (zzap) r4;
        String r0 = this.zza;
        String r2 = r42.zza;
        if (r0 != null) goto L11;
        if (r2 == null) goto L16;
    L14:
        return false;
    L16:
        return this.zzb.equals(r42.zzb);
    L11:
        if (r0.equals(r2) == true) goto L16;
        goto L14
    }

    public final int hashCode() {
        String r0 = this.zza;
        if (r0 == null) goto L5;
        int r02 = r0.hashCode();
    L7:
        return (r02 * 31) + this.zzb.hashCode();
    L5:
        r02 = 0;
        goto L7
    }

    public final String zzb() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final String zzc() {
        throw new IllegalStateException("Statement cannot be cast as String");
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final zzao zzcG(String r1, zzg r2, List r3) {
        throw new IllegalStateException("Statement is not an evaluated entity");
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final Double zzd() {
        throw new IllegalStateException("Statement cannot be cast as Double");
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final Boolean zze() {
        throw new IllegalStateException("Statement cannot be cast as Boolean");
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final Iterator zzf() {
        return null;
    }

    public final ArrayList zzg() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final zzao zzt() {
        return this;
    }
}
