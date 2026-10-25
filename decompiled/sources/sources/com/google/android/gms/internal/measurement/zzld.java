package com.google.android.gms.internal.measurement;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes4.dex */
public abstract class zzld {
    private static final ConcurrentMap zza = null;

    static {
        zza = new ConcurrentHashMap();
    }

    public static void zza() {
        Iterator r0 = zza.values().iterator();
        if (r0.hasNext() == true) goto L5;
        return;
    L5:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r0.next());
        throw null;
    }
}
