package com.google.android.recaptcha.internal;

import c.f$$ExternalSyntheticBUOutline2;
import java.util.Arrays;
import kotlin.UByte;

/* loaded from: classes5.dex */
public final class zzady {
    private final byte[] zza;

    private zzady(byte[] r1, int r2, int r3) {
        byte[] r22 = new byte[r3];
        this.zza = r22;
        System.arraycopy(r1, 0, r22, 0, r3);
    }

    public static zzady zza(byte[] r2) {
        if (r2 != null) goto L4;
        f$$ExternalSyntheticBUOutline2.m("data must be non-null");
        return null;
    L4:
        return zzb(r2, 0, r2.length);
    }

    public static zzady zzb(byte[] r1, int r2, int r3) {
        if (r1 == null) goto L8;
        int r22 = r1.length;
        if (r3 <= r22) goto L7;
        r3 = r22;
    L7:
        return new zzady(r1, 0, r3);
    L8:
        f$$ExternalSyntheticBUOutline2.m("data must be non-null");
        return null;
    }

    public final boolean equals(Object r2) {
        if ((r2 instanceof zzady) == true) goto L7;
        return false;
    L7:
        return Arrays.equals(((zzady) r2).zza, this.zza);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.zza);
    }

    public final String toString() {
        byte[] r5 = this.zza;
        int r0 = r5.length;
        StringBuilder r1 = new StringBuilder(r0 + r0);
        int r02 = 0;
    L4:
        if (r02 >= r5.length) goto L6;
        byte r2 = r5[r02];
        r1.append("0123456789abcdef".charAt((r2 & UByte.MAX_VALUE) >> 4));
        r1.append("0123456789abcdef".charAt(r2 & 15));
        r02 = r02 + 1;
        goto L4
    L6:
        String r52 = r1.toString();
        StringBuilder r12 = new StringBuilder(r52.length() + 7);
        r12.append("Bytes(");
        r12.append(r52);
        r12.append(")");
        return r12.toString();
    }

    public final byte[] zzc() {
        byte[] r3 = this.zza;
        int r0 = r3.length;
        byte[] r1 = new byte[r0];
        System.arraycopy(r3, 0, r1, 0, r0);
        return r1;
    }

    public final int zzd() {
        return this.zza.length;
    }
}
