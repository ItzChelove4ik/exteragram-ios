package com.google.android.gms.internal.mlkit_common;

import java.util.Set;

/* loaded from: classes4.dex */
public abstract class zzaj extends zzab implements Set {
    private transient zzaf zza;

    public zzaj() {
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(Object r5) {
        if (r5 == this) goto L16;
        if (r5 != this) goto L7;
        return true;
    L7:
        if ((r5 instanceof Set) == false) goto L15;
        Set r52 = (Set) r5;
        if (size() != r52.size()) goto L15;
        if (containsAll(r52) == true) goto L14;
        return false;
    L14:
        return true;
    L15:
        return false;
    L16:
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public final int hashCode() {
        return zzar.zza(this);
    }

    public final zzaf zzf() {
        zzaf r0 = this.zza;
        if (r0 != null) goto L6;
        zzaf r02 = zzg();
        this.zza = r02;
        return r02;
    L6:
        return r0;
    }

    public zzaf zzg() {
        Object[] r1 = toArray();
        int r0 = zzaf.$r8$clinit;
        return zzaf.zzg(r1, r1.length);
    }
}
