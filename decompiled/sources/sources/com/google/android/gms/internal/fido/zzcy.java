package com.google.android.gms.internal.fido;

import java.util.Iterator;
import java.util.Set;

/* loaded from: classes4.dex */
public abstract class zzcy {
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
}
