package com.google.android.gms.internal.cast;

import c.f$$ExternalSyntheticBUOutline2;
import com.google.android.gms.internal.cast.zzwy;
import com.google.android.gms.internal.cast.zzwz;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class zzwz<MessageType extends zzwz<MessageType, BuilderType>, BuilderType extends zzwy<MessageType, BuilderType>> implements zzzi {
    protected transient int zza;

    public zzwz() {
        this.zza = 0;
    }

    public static void zzu(Iterable r4, List r5) {
        byte[] r0 = zzym.zzb;
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
        int r43 = r5.size() - r03;
        StringBuilder r22 = new StringBuilder(String.valueOf(r43).length() + 26);
        r22.append("Element at index ");
        r22.append(r43);
        r22.append(" is null.");
        String r44 = r22.toString();
        int r12 = r5.size();
    L13:
        r12 = r12 - 1;
        if (r12 < r03) goto L16;
        r5.remove(r12);
        goto L13
    L16:
        f$$ExternalSyntheticBUOutline2.m(r44);
        return;
    L19:
        return;
    L6:
        if ((r5 instanceof zzzq) == false) goto L8;
        ((zzzq) r5).zze(r5.size() + r02);
        goto L8
    }

    public final byte[] zzs() {
        int r0 = zzE();     // Catch: IOException -> L4
        byte[] r1 = new byte[r0];     // Catch: IOException -> L4
        int r2 = zzxp.$r8$clinit;     // Catch: IOException -> L4
        zzxn r22 = new zzxn(r1, 0, r0);     // Catch: IOException -> L4
        zzD(r22);     // Catch: IOException -> L4
        r22.zzx();     // Catch: IOException -> L4
        return r1;
    L4:
        e = move-exception;
        String r4 = getClass().getName();
        zzwz$$ExternalSyntheticBUOutline0.m(r4.length() + 72, r4, " to a byte array threw an IOException (should never happen).", e);
        return null;
    }

    public abstract int zzt(zzzs r1);
}
