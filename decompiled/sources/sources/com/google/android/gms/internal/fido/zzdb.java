package com.google.android.gms.internal.fido;

import java.util.Comparator;
import java.util.SortedSet;

/* loaded from: classes4.dex */
abstract class zzdb {
    public static boolean zza(Comparator r1, Iterable r2) {
        r1.getClass();
        r2.getClass();
        if ((r2 instanceof SortedSet) == false) goto L8;
        Comparator r22 = ((SortedSet) r2).comparator();
        if (r22 != null) goto L11;
        r22 = zzcq.zza;
    L11:
        return r1.equals(r22);
    L8:
        if ((r2 instanceof zzda) == false) goto L12;
        r22 = ((zzda) r2).comparator();
        goto L11
    L12:
        return false;
    }
}
