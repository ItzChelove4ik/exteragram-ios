package com.google.android.gms.internal.fido;

import java.util.Arrays;

/* loaded from: classes4.dex */
public final class zzhn extends zzhp {
    private final String zza;

    public zzhn(String r1) {
        this.zza = r1;
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object r4) {
        zzhp r42 = (zzhp) r4;
        int r0 = r42.zza();
        if (zzhp.zzd((byte) 96) == r0) goto L6;
        int r3 = r42.zza();
        return zzhp.zzd((byte) 96) - r3;
    L6:
        String r32 = this.zza;
        String r43 = ((zzhn) r42).zza;
        int r02 = r43.length();
        if (r32.length() == r02) goto L11;
        int r44 = r43.length();
        return r32.length() - r44;
    L11:
        return r32.compareTo(r43);
    }

    public final boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if (r4 != null) goto L9;
        return false;
    L9:
        if (zzhn.class == r4.getClass()) goto L12;
        return false;
    L12:
        return this.zza.equals(((zzhn) r4).zza);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(zzhp.zzd((byte) 96)), this.zza});
    }

    public final String toString() {
        return "\"" + this.zza + "\"";
    }

    @Override // com.google.android.gms.internal.fido.zzhp
    public final int zza() {
        return zzhp.zzd((byte) 96);
    }
}
