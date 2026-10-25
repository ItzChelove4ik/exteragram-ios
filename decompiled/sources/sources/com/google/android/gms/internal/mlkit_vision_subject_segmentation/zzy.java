package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import java.util.Map;
import java.util.Set;

/* loaded from: classes4.dex */
abstract class zzy implements zzbi {
    private transient Set zza;
    private transient Map zzb;

    public zzy() {
    }

    public final boolean equals(Object r2) {
        if (r2 != this) goto L6;
        return true;
    L6:
        if ((r2 instanceof zzbi) == true) goto L10;
        return false;
    L10:
        return zzn().equals(((zzbi) r2).zzn());
    }

    public final int hashCode() {
        return zzn().hashCode();
    }

    public final String toString() {
        return zzn().toString();
    }

    public abstract Map zzh();

    public abstract Set zzi();

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzbi
    public final Map zzn() {
        Map r0 = this.zzb;
        if (r0 != null) goto L6;
        Map r02 = zzh();
        this.zzb = r02;
        return r02;
    L6:
        return r0;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzbi
    public final Set zzo() {
        Set r0 = this.zza;
        if (r0 != null) goto L6;
        Set r02 = zzi();
        this.zza = r02;
        return r02;
    L6:
        return r0;
    }
}
