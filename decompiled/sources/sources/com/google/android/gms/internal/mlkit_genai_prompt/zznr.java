package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.Comparator;
import java.util.SortedSet;

/* loaded from: classes4.dex */
abstract class zznr {
    public static boolean zza(Comparator r1, Iterable r2) {
        r1.getClass();
        r2.getClass();
        if ((r2 instanceof SortedSet) == false) goto L8;
        Comparator r22 = ((SortedSet) r2).comparator();
        if (r22 != null) goto L11;
        r22 = zzmz.zza;
    L11:
        return r1.equals(r22);
    L8:
        if ((r2 instanceof zznq) == false) goto L12;
        r22 = ((zznq) r2).comparator();
        goto L11
    L12:
        return false;
    }
}
