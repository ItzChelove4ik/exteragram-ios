package com.google.android.gms.internal.fido;

import java.io.Serializable;
import java.util.Comparator;

/* loaded from: classes4.dex */
final class zzbw extends zzcs implements Serializable {
    final Comparator zza;

    public zzbw(Comparator r1) {
        r1.getClass();
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.fido.zzcs, java.util.Comparator
    public final int compare(Object r1, Object r2) {
        return this.zza.compare(r1, r2);
    }

    @Override // java.util.Comparator
    public final boolean equals(Object r2) {
        if (r2 != this) goto L6;
        return true;
    L6:
        if ((r2 instanceof zzbw) == true) goto L8;
        return false;
    L8:
        return this.zza.equals(((zzbw) r2).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final String toString() {
        return this.zza.toString();
    }
}
