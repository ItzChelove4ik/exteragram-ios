package com.google.android.gms.internal.fido;

import java.io.Serializable;

/* loaded from: classes4.dex */
final class zzcw extends zzcs implements Serializable {
    static final zzcw zza = null;

    static {
        zza = new zzcw();
    }

    private zzcw() {
    }

    @Override // com.google.android.gms.internal.fido.zzcs, java.util.Comparator
    public final /* bridge */ /* synthetic */ int compare(Object r1, Object r2) {
        Comparable r12 = (Comparable) r1;
        Comparable r22 = (Comparable) r2;
        r12.getClass();
        if (r12 != r22) goto L7;
        return 0;
    L7:
        return r22.compareTo(r12);
    }

    public final String toString() {
        return "Ordering.natural().reverse()";
    }

    @Override // com.google.android.gms.internal.fido.zzcs
    public final zzcs zza() {
        return zzcq.zza;
    }
}
