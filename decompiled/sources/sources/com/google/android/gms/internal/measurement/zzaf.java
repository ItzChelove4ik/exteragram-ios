package com.google.android.gms.internal.measurement;

import com.google.protobuf.FieldSet$$ExternalSyntheticBUOutline0;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzaf implements zzao {
    private final boolean zza;

    public zzaf(Boolean r1) {
        if (r1 != null) goto L7;
        boolean r12 = false;
    L5:
        this.zza = r12;
        return;
    L7:
        r12 = r1.booleanValue();
        goto L5
    }

    public final boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if ((r4 instanceof zzaf) == true) goto L9;
        return false;
    L9:
        if (this.zza != ((zzaf) r4).zza) goto L11;
        return true;
    L11:
        return false;
    }

    public final int hashCode() {
        return Boolean.valueOf(this.zza).hashCode();
    }

    public final String toString() {
        return String.valueOf(this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final String zzc() {
        return Boolean.toString(this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final zzao zzcG(String r1, zzg r2, List r3) {
        boolean r22 = "toString".equals(r1);
        boolean r0 = this.zza;
        if (r22 == true) goto L5;
        FieldSet$$ExternalSyntheticBUOutline0.m("%s.%s is not a function.", new Object[]{Boolean.toString(r0), r1});
        return null;
    L5:
        return new zzas(Boolean.toString(r0));
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final Double zzd() {
        if (true == this.zza) goto L5;
        double r0 = 0.0d;
    L7:
        return Double.valueOf(r0);
    L5:
        r0 = 1.0d;
        goto L7
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final Boolean zze() {
        return Boolean.valueOf(this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final Iterator zzf() {
        return null;
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final zzao zzt() {
        return new zzaf(Boolean.valueOf(this.zza));
    }
}
