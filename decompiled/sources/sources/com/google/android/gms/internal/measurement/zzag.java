package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzag implements zzao {
    private final zzao zza;
    private final String zzb;

    public zzag(String r2) {
        this.zza = zzao.zzf;
        this.zzb = r2;
    }

    public zzag(String r1, zzao r2) {
        this.zza = r2;
        this.zzb = r1;
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzag) == true) goto L8;
        return false;
    L8:
        zzag r52 = (zzag) r5;
        if (this.zzb.equals(r52.zzb) == true) goto L11;
    L13:
        return false;
    L11:
        if (this.zza.equals(r52.zza) == false) goto L13;
        return true;
    }

    public final int hashCode() {
        return (this.zzb.hashCode() * 31) + this.zza.hashCode();
    }

    public final zzao zzb() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final String zzc() {
        throw new IllegalStateException("Control is not a String");
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final zzao zzcG(String r1, zzg r2, List r3) {
        throw new IllegalStateException("Control does not have functions");
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final Double zzd() {
        throw new IllegalStateException("Control is not a double");
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final Boolean zze() {
        throw new IllegalStateException("Control is not a boolean");
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final Iterator zzf() {
        return null;
    }

    public final String zzg() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final zzao zzt() {
        zzao r1 = this.zza;
        return new zzag(this.zzb, r1.zzt());
    }
}
