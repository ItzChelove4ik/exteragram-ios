package com.google.android.gms.internal.fido;

import java.io.Serializable;
import java.util.Arrays;

/* loaded from: classes4.dex */
final class zzbs implements Serializable, zzbp {
    final Object zza;

    public zzbs(Object r1) {
        this.zza = r1;
    }

    public final boolean equals(Object r2) {
        if ((r2 instanceof zzbs) == true) goto L5;
        return false;
    L5:
        return zzbk.zza(this.zza, ((zzbs) r2).zza);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza});
    }

    public final String toString() {
        return "Suppliers.ofInstance(" + this.zza.toString() + ")";
    }

    @Override // com.google.android.gms.internal.fido.zzbp
    public final Object zza() {
        return this.zza;
    }
}
