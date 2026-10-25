package com.google.android.gms.internal.measurement;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.collection.ArrayMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
public abstract class zzma {
    private static final Map zza = null;

    static {
        zza = new ArrayMap();
    }

    public static synchronized void zza() {
        monitor-enter(zzma.class);
        Map r1 = zza;     // Catch: Throwable -> L9
        Iterator r2 = r1.values().iterator();     // Catch: Throwable -> L9
        if (r2.hasNext() == true) goto L11;
        r1.clear();     // Catch: Throwable -> L9
        monitor-exit(zzma.class);
        return;
    L11:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2.next());     // Catch: Throwable -> L9
        throw null;     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        throw th;
    }
}
