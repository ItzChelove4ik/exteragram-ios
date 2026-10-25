package com.google.android.gms.internal.mlkit_vision_label;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes4.dex */
public abstract class zzcb {
    public static int zza(Set r3) {
        Iterator r32 = r3.iterator();
        int r1 = 0;
    L4:
        if (r32.hasNext() == false) goto L10;
        Object r2 = r32.next();
        if (r2 == null) goto L8;
        int r22 = r2.hashCode();
    L9:
        r1 = r1 + r22;
        goto L4
    L8:
        r22 = 0;
        goto L9
    L10:
        return r1;
    }

    public static boolean zzb(Set r2, Collection r3) {
        r3.getClass();
        if ((r3 instanceof zzbs) == false) goto L6;
        r3 = ((zzbs) r3).zza();
    L6:
        if ((r3 instanceof Set) == false) goto L17;
        if (r3.size() <= r2.size()) goto L17;
        Iterator r22 = r2.iterator();
        boolean r0 = false;
    L11:
        if (r22.hasNext() == false) goto L15;
        if (r3.contains(r22.next()) == false) goto L11;
        r22.remove();
        r0 = true;
        goto L11
    L15:
        return r0;
    L17:
        return zzc(r2, r3.iterator());
    }

    public static boolean zzc(Set r2, Iterator r3) {
        boolean r0 = false;
    L4:
        if (r3.hasNext() == false) goto L6;
        r0 = r0 | r2.remove(r3.next());
        goto L4
    L6:
        return r0;
    }
}
