package com.google.android.gms.internal.fido;

import java.util.Comparator;
import kotlin.UByte;

/* loaded from: classes4.dex */
enum zzgk extends Enum implements Comparator {
    public static final zzgk zza = null;
    private static final /* synthetic */ zzgk[] zzb = null;

    static {
        zzgk r0 = new zzgk("INSTANCE", 0);
        zza = r0;
        zzb = new zzgk[]{r0};
    }

    zzgk(String r1, int r2) {
    }

    public static zzgk[] values() {
        return (zzgk[]) zzb.clone();
    }

    @Override // java.util.Comparator
    public final /* bridge */ /* synthetic */ int compare(Object r4, Object r5) {
        byte[] r42 = (byte[]) r4;
        byte[] r52 = (byte[]) r5;
        int r3 = Math.min(r42.length, r52.length);
        int r0 = 0;
    L3:
        if (r0 >= r3) goto L9;
        byte r1 = r42[r0];
        byte r2 = r52[r0];
        int r12 = (r1 & UByte.MAX_VALUE) - (r2 & UByte.MAX_VALUE);
        if (r12 != 0) goto L6;
        r0 = r0 + 1;
        goto L3
    L6:
        return r12;
    L9:
        return r42.length - r52.length;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "UnsignedBytes.lexicographicalComparator() (pure Java version)";
    }
}
