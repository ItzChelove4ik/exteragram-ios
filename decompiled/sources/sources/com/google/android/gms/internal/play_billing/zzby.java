package com.google.android.gms.internal.play_billing;

import java.util.Iterator;

/* loaded from: classes5.dex */
public abstract class zzby {
    public static Object zza(Iterable r0, Object r1) {
        Iterator r02 = r0.iterator();
        if (r02.hasNext() == true) goto L5;
        return null;
    L5:
        return r02.next();
    }
}
