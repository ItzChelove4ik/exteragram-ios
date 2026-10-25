package com.google.android.gms.internal.vision;

import c.f$$ExternalSyntheticBUOutline2;
import com.google.android.gms.internal.cast.zzwz$$ExternalSyntheticBUOutline0;
import com.google.android.gms.internal.vision.zzhe;
import com.google.android.gms.internal.vision.zzhf;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public abstract class zzhf<MessageType extends zzhf<MessageType, BuilderType>, BuilderType extends zzhe<MessageType, BuilderType>> implements zzkk {
    protected int zza;

    public zzhf() {
        this.zza = 0;
    }

    public static <T> void zza(Iterable<T> r6, List<? super T> r7) {
        zzjf.zza(r6);
        if ((r6 instanceof zzjv) == false) goto L19;
        List<?> r62 = ((zzjv) r6).zzd();
        zzjv r0 = (zzjv) r7;
        int r72 = r7.size();
        Iterator<?> r63 = r62.iterator();
    L6:
        if (r63.hasNext() == false) goto L38;
        Object r4 = r63.next();
        if (r4 == null) goto L9;
        if ((r4 instanceof zzht) == true) goto L16;
        r0.add((String) r4);
        goto L6
    L16:
        r0.zza((zzht) r4);
        goto L6
    L9:
        int r64 = r0.size() - r72;
        StringBuilder r42 = new StringBuilder(37);
        r42.append("Element at index ");
        r42.append(r64);
        r42.append(" is null.");
        String r65 = r42.toString();
        int r1 = r0.size() - 1;
    L10:
        if (r1 < r72) goto L12;
        r0.remove(r1);
        r1 = r1 - 1;
        goto L10
    L12:
        f$$ExternalSyntheticBUOutline2.m(r65);
        return;
    L38:
        return;
    L19:
        if ((r6 instanceof zzkw) == false) goto L23;
        r7.addAll((Collection) r6);
        return;
    L23:
        if ((r7 instanceof ArrayList) == true) goto L25;
    L27:
        int r02 = r7.size();
        Iterator<T> r66 = r6.iterator();
    L29:
        if (r66.hasNext() == false) goto L50;
        T r43 = r66.next();
        if (r43 == null) goto L32;
        r7.add(r43);
        goto L29
    L32:
        int r67 = r7.size() - r02;
        StringBuilder r44 = new StringBuilder(37);
        r44.append("Element at index ");
        r44.append(r67);
        r44.append(" is null.");
        String r68 = r44.toString();
        int r12 = r7.size() - 1;
    L33:
        if (r12 < r02) goto L35;
        r7.remove(r12);
        r12 = r12 - 1;
        goto L33
    L35:
        f$$ExternalSyntheticBUOutline2.m(r68);
        return;
    L50:
        return;
    L25:
        if ((r6 instanceof Collection) == false) goto L27;
        ((ArrayList) r7).ensureCapacity(r7.size() + ((Collection) r6).size());
        goto L27
    }

    public abstract void zzb(int r1);

    public final byte[] zzh() {
        byte[] r0 = new byte[zzm()];     // Catch: IOException -> L4
        zzii r1 = zzii.zza(r0);     // Catch: IOException -> L4
        zza(r1);     // Catch: IOException -> L4
        r1.zzb();     // Catch: IOException -> L4
        return r0;
    L4:
        e = move-exception;
        String r3 = getClass().getName();
        zzwz$$ExternalSyntheticBUOutline0.m((r3.length() + 62) + "byte array".length(), r3, " to a byte array threw an IOException (should never happen).", e);
        return null;
    }

    public abstract int zzi();
}
