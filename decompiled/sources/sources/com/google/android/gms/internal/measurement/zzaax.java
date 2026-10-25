package com.google.android.gms.internal.measurement;

import java.util.Comparator;

/* loaded from: classes4.dex */
final class zzaax implements Comparator {
    public zzaax() {
    }

    @Override // java.util.Comparator
    public final int compare(Object r2, Object r3) {
        zzabd r1 = zzabd.zza(r2);
        zzabd r0 = zzabd.zza(r3);
        if (r1 != r0) goto L23;
        int r12 = r1.ordinal();
        if (r12 == 0) goto L21;
        if (r12 == 1) goto L19;
        if (r12 == 2) goto L17;
        if (r12 != 3) goto L15;
        return ((Double) r2).compareTo((Double) r3);
    L15:
        throw null;
    L17:
        return ((Long) r2).compareTo((Long) r3);
    L19:
        return ((String) r2).compareTo((String) r3);
    L21:
        return ((Boolean) r2).compareTo((Boolean) r3);
    L23:
        return r1.compareTo(r0);
    }
}
