package com.google.android.gms.internal.play_billing;

import c.f$$ExternalSyntheticBUOutline2;
import com.google.android.gms.internal.play_billing.zzdr;
import com.google.android.gms.internal.play_billing.zzds;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes5.dex */
public abstract class zzds<MessageType extends zzds<MessageType, BuilderType>, BuilderType extends zzdr<MessageType, BuilderType>> implements zzgl {
    protected int zza;

    public zzds() {
        this.zza = 0;
    }

    public static void zzg(Iterable r4, List r5) {
        byte[] r0 = zzfo.zzb;
        int r02 = ((Collection) r4).size();
        if ((r5 instanceof ArrayList) == false) goto L6;
        ((ArrayList) r5).ensureCapacity(r5.size() + r02);
    L8:
        int r03 = r5.size();
        List r42 = (List) r4;
        int r1 = r42.size();
        int r2 = 0;
    L9:
        if (r2 >= r1) goto L19;
        Object r3 = r42.get(r2);
        if (r3 == null) goto L12;
        r5.add(r3);
        r2 = r2 + 1;
        goto L9
    L12:
        String r43 = "Element at index " + (r5.size() - r03) + " is null.";
        int r12 = r5.size();
    L13:
        r12 = r12 - 1;
        if (r12 < r03) goto L16;
        r5.remove(r12);
        goto L13
    L16:
        f$$ExternalSyntheticBUOutline2.m(r43);
        return;
    L19:
        return;
    L6:
        if ((r5 instanceof zzgt) == false) goto L8;
        ((zzgt) r5).zzf(r5.size() + r02);
        goto L8
    }

    public final byte[] zzM() {
        int r0 = zzj();     // Catch: IOException -> L4
        byte[] r1 = new byte[r0];     // Catch: IOException -> L4
        int r2 = zzep.$r8$clinit;     // Catch: IOException -> L4
        zzem r22 = new zzem(r1, 0, r0);     // Catch: IOException -> L4
        zzL(r22);     // Catch: IOException -> L4
        r22.zzE();     // Catch: IOException -> L4
        return r1;
    L4:
        e = move-exception;
        throw new RuntimeException("Serializing " + getClass().getName() + " to a byte array threw an IOException (should never happen).", e);
    }

    public abstract int zze(zzgv r1);
}
