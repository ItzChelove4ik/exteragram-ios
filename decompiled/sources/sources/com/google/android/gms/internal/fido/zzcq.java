package com.google.android.gms.internal.fido;

import java.io.Serializable;

/* loaded from: classes4.dex */
final class zzcq extends zzcs implements Serializable {
    static final zzcq zza = null;

    static {
        zza = new zzcq();
    }

    private zzcq() {
    }

    @Override // com.google.android.gms.internal.fido.zzcs, java.util.Comparator
    public final /* bridge */ /* synthetic */ int compare(Object r1, Object r2) {
        Comparable r12 = (Comparable) r1;
        Comparable r22 = (Comparable) r2;
        r12.getClass();
        r22.getClass();
        return r12.compareTo(r22);
    }

    public final String toString() {
        return "Ordering.natural()";
    }

    @Override // com.google.android.gms.internal.fido.zzcs
    public final zzcs zza() {
        return zzcw.zza;
    }
}
