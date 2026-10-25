package com.google.android.gms.internal.fido;

import java.io.Serializable;

/* loaded from: classes4.dex */
final class zzcx extends zzcs implements Serializable {
    final zzcs zza;

    public zzcx(zzcs r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.fido.zzcs, java.util.Comparator
    public final int compare(Object r1, Object r2) {
        return this.zza.compare(r2, r1);
    }

    @Override // java.util.Comparator
    public final boolean equals(Object r2) {
        if (r2 != this) goto L6;
        return true;
    L6:
        if ((r2 instanceof zzcx) == true) goto L8;
        return false;
    L8:
        return this.zza.equals(((zzcx) r2).zza);
    }

    public final int hashCode() {
        return -this.zza.hashCode();
    }

    public final String toString() {
        return this.zza.toString().concat(".reverse()");
    }

    @Override // com.google.android.gms.internal.fido.zzcs
    public final zzcs zza() {
        return this.zza;
    }
}
