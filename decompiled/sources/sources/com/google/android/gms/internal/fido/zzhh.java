package com.google.android.gms.internal.fido;

import java.util.Arrays;

/* loaded from: classes4.dex */
public final class zzhh extends zzhp {
    private final boolean zza;

    public zzhh(boolean r1) {
        this.zza = r1;
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object r4) {
        zzhp r42 = (zzhp) r4;
        if (zzhp.zzd((byte) -32) != r42.zza()) goto L5;
        zzhh r43 = (zzhh) r42;
        int r0 = 21;
        if (true == this.zza) goto L9;
        int r3 = 20;
    L11:
        if (true == r43.zza) goto L14;
        r0 = 20;
    L14:
        return r3 - r0;
    L9:
        r3 = 21;
        goto L11
    L5:
        return zzhp.zzd((byte) -32) - r42.zza();
    }

    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L9;
        return false;
    L9:
        if (zzhh.class == r5.getClass()) goto L12;
        return false;
    L12:
        if (this.zza != ((zzhh) r5).zza) goto L14;
        return true;
    L14:
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(zzhp.zzd((byte) -32)), Boolean.valueOf(this.zza)});
    }

    public final String toString() {
        return Boolean.toString(this.zza);
    }

    @Override // com.google.android.gms.internal.fido.zzhp
    public final int zza() {
        return zzhp.zzd((byte) -32);
    }
}
