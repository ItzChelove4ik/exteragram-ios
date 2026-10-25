package com.google.android.gms.internal.mlkit_vision_label;

import java.util.Map;
import java.util.Set;

/* loaded from: classes4.dex */
abstract class zzai implements zzbr {
    private transient Set zza;
    private transient Map zzb;

    public zzai() {
    }

    public final boolean equals(Object r2) {
        if (r2 != this) goto L6;
        return true;
    L6:
        if ((r2 instanceof zzbr) == true) goto L10;
        return false;
    L10:
        return zzp().equals(((zzbr) r2).zzp());
    }

    public final int hashCode() {
        return zzp().hashCode();
    }

    public final String toString() {
        return ((zzy) zzp()).zza.toString();
    }

    public abstract Map zzk();

    public abstract Set zzl();

    @Override // com.google.android.gms.internal.mlkit_vision_label.zzbr
    public final Map zzp() {
        Map r0 = this.zzb;
        if (r0 != null) goto L6;
        Map r02 = zzk();
        this.zzb = r02;
        return r02;
    L6:
        return r0;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_label.zzbr
    public final Set zzq() {
        Set r0 = this.zza;
        if (r0 != null) goto L6;
        Set r02 = zzl();
        this.zza = r02;
        return r02;
    L6:
        return r0;
    }
}
