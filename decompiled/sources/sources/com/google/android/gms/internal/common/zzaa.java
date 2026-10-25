package com.google.android.gms.internal.common;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.Arrays;

/* loaded from: classes.dex */
abstract class zzaa extends zzab {
    Object[] zza;
    int zzb;
    boolean zzc;

    public zzaa(int r1) {
        this.zza = new Object[4];
        this.zzb = 0;
    }

    public final zzaa zza(Object r5) {
        r5.getClass();
        int r0 = this.zza.length;
        int r1 = this.zzb;
        int r2 = r1 + 1;
        if (r2 < 0) goto L17;
        if (r2 > r0) goto L6;
        int r3 = r0;
    L11:
        if (r3 <= r0) goto L13;
    L14:
        this.zza = Arrays.copyOf(this.zza, r3);
        this.zzc = false;
    L15:
        Object[] r02 = this.zza;
        int r12 = this.zzb;
        this.zzb = r12 + 1;
        r02[r12] = r5;
        return this;
    L13:
        if (this.zzc == false) goto L15;
    L6:
        r3 = ((r0 >> 1) + r0) + 1;
        if (r3 >= r2) goto L9;
        int r13 = Integer.highestOneBit(r1);
        r3 = r13 + r13;
    L9:
        if (r3 >= 0) goto L11;
        r3 = Integer.MAX_VALUE;
        goto L11
    L17:
        f$$ExternalSyntheticBUOutline1.m("cannot store more than Integer.MAX_VALUE elements");
        return null;
    }
}
