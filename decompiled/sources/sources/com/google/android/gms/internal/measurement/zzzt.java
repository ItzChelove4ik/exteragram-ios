package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Set;

/* loaded from: classes4.dex */
public abstract class zzzt {
    private static final zzzp zza = null;
    private static final zzzo zzb = null;

    static {
        zza = new zzzr();
        zzb = new zzzs();
    }

    public static zzzm zza(Set r3) {
        zzzm r1 = new zzzm(zza, null);
        r1.zza(zzb);
        Iterator r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L6;
        r1.zzb((zzyl) r32.next());
        goto L4
    L6:
        return r1;
    }
}
