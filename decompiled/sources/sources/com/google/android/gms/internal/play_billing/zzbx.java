package com.google.android.gms.internal.play_billing;

import java.util.Set;

/* loaded from: classes5.dex */
public abstract class zzbx extends zzbq implements Set {
    private transient zzbt zza;

    public zzbx() {
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
        return zzcg.zza(this);
    }

    @Override // com.google.android.gms.internal.play_billing.zzbq
    public zzbt zzd() {
        zzbt r0 = this.zza;
        if (r0 != null) goto L6;
        zzbt r02 = zzh();
        this.zza = r02;
        return r02;
    L6:
        return r0;
    }

    public zzbt zzh() {
        Object[] r1 = toArray();
        int r0 = zzbt.$r8$clinit;
        return zzbt.zzi(r1, r1.length);
    }
}
