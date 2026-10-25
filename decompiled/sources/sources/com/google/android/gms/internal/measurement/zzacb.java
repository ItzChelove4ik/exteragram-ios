package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.cast.zzwz$$ExternalSyntheticBUOutline0;
import com.google.android.gms.internal.measurement.zzaca;
import com.google.android.gms.internal.measurement.zzacb;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class zzacb<MessageType extends zzacb<MessageType, BuilderType>, BuilderType extends zzaca<MessageType, BuilderType>> implements zzafc {
    protected transient int zza;

    public zzacb() {
        this.zza = 0;
    }

    public static void zzcg(Iterable r0, List r1) {
        zzaca.zzaV(r0, r1);
    }

    public final byte[] zzcd() {
        int r0 = zzcq();     // Catch: IOException -> L4
        byte[] r1 = new byte[r0];     // Catch: IOException -> L4
        int r2 = zzada.$r8$clinit;     // Catch: IOException -> L4
        zzacx r22 = new zzacx(r1, 0, r0);     // Catch: IOException -> L4
        zzcH(r22);     // Catch: IOException -> L4
        r22.zzH();     // Catch: IOException -> L4
        return r1;
    L4:
        e = move-exception;
        String r4 = getClass().getName();
        zzwz$$ExternalSyntheticBUOutline0.m(r4.length() + 72, r4, " to a byte array threw an IOException (should never happen).", e);
        return null;
    }

    @Override // com.google.android.gms.internal.measurement.zzafc
    public final void zzce(OutputStream r3) {
        int r0 = zzcq();
        int r1 = zzada.$r8$clinit;
        if (r0 <= 4096) goto L5;
        r0 = 4096;
    L5:
        zzacz r12 = new zzacz(r3, r0);
        zzcH(r12);
        r12.zzx();
    }

    public abstract int zzcf(zzafp r1);
}
