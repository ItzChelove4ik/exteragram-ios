package com.google.android.gms.internal.fido;

import java.util.Arrays;

/* loaded from: classes4.dex */
public final class zzhk extends zzhp {
    private final long zza;

    public zzhk(long r1) {
        this.zza = r1;
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object r3) {
        zzhp r32 = (zzhp) r3;
        if (zza() != r32.zza()) goto L5;
        long r0 = Math.abs(this.zza);
        long r2 = Math.abs(((zzhk) r32).zza);
        if (r0 >= r2) goto L10;
        return -1;
    L10:
        if (r0 <= r2) goto L13;
        return 1;
    L13:
        return 0;
    L5:
        return zza() - r32.zza();
    }

    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L9;
        return false;
    L9:
        if (zzhk.class == r5.getClass()) goto L12;
        return false;
    L12:
        if (this.zza != ((zzhk) r5).zza) goto L14;
        return true;
    L14:
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(zza()), Long.valueOf(this.zza)});
    }

    public final String toString() {
        return Long.toString(this.zza);
    }

    @Override // com.google.android.gms.internal.fido.zzhp
    public final int zza() {
        if (this.zza < 0) goto L5;
        byte r4 = 0;
    L7:
        return zzhp.zzd(r4);
    L5:
        r4 = 32;
        goto L7
    }

    public final long zzc() {
        return this.zza;
    }
}
