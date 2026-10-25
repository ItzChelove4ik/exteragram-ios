package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public abstract class zzfz {
    protected volatile int zzrs;

    public zzfz() {
        this.zzrs = -1;
    }

    public static final void zza(zzfz r0, byte[] r1, int r2, int r3) {
        zzfs r12 = zzfs.zzh(r1, 0, r3);     // Catch: IOException -> L5
        r0.zza(r12);     // Catch: IOException -> L5
        r12.zzem();     // Catch: IOException -> L5
        return;
    L5:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Serializing to a byte array threw an IOException (should never happen).", e);
    }

    public String toString() {
        return zzga.zza(this);
    }

    public abstract void zza(zzfs r1);

    public final int zzas() {
        int r0 = zzen();
        this.zzrs = r0;
        return r0;
    }

    public abstract int zzen();

    public zzfz zzep() {
        return (zzfz) super.clone();
    }
}
