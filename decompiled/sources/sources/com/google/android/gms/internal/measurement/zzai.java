package com.google.android.gms.internal.measurement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public abstract class zzai implements zzao, zzak {
    protected final String zzd;
    protected final Map zze;

    public zzai(String r2) {
        this.zze = new HashMap();
        this.zzd = r2;
    }

    public final boolean equals(Object r3) {
        if (this != r3) goto L6;
        return true;
    L6:
        if ((r3 instanceof zzai) == true) goto L8;
        return false;
    L8:
        zzai r32 = (zzai) r3;
        String r2 = this.zzd;
        if (r2 != null) goto L11;
        return false;
    L11:
        return r2.equals(r32.zzd);
    }

    public final int hashCode() {
        String r0 = this.zzd;
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0.hashCode();
    }

    public abstract zzao zza(zzg r1, List r2);

    @Override // com.google.android.gms.internal.measurement.zzao
    public final String zzc() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final zzao zzcG(String r2, zzg r3, List r4) {
        if ("toString".equals(r2) == false) goto L7;
        return new zzas(this.zzd);
    L7:
        return zzak.zzu(this, new zzas(r2), r3, r4);
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final Double zzd() {
        return Double.valueOf(Double.NaN);
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final Boolean zze() {
        return Boolean.TRUE;
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final Iterator zzf() {
        return zzak.zzv(this.zze);
    }

    public final String zzg() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.measurement.zzak
    public final boolean zzj(String r1) {
        return this.zze.containsKey(r1);
    }

    @Override // com.google.android.gms.internal.measurement.zzak
    public final zzao zzk(String r2) {
        Map r1 = this.zze;
        if (r1.containsKey(r2) == false) goto L7;
        return (zzao) r1.get(r2);
    L7:
        return zzao.zzf;
    }

    @Override // com.google.android.gms.internal.measurement.zzak
    public final void zzm(String r1, zzao r2) {
        Map r0 = this.zze;
        if (r2 != null) goto L6;
        r0.remove(r1);
        return;
    L6:
        r0.put(r1, r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public zzao zzt() {
        return this;
    }
}
