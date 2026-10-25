package com.google.android.gms.internal.measurement;

import android.os.StrictMode;
import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.ServiceLoader;

/* loaded from: classes4.dex */
abstract class zzrj {
    static final zzrl zza = null;

    static {
        StrictMode.ThreadPolicy r1 = StrictMode.allowThreadDiskReads();
        Iterator r0 = ServiceLoader.load(zzrl.class, zzrl.class.getClassLoader()).iterator();     // Catch: Throwable -> L7
        if (r0.hasNext() == false) goto L9;
        zzrl r2 = (zzrl) r0.next();     // Catch: Throwable -> L7
        Preconditions.checkState(!r0.hasNext(), "Expected at most one FlagsService");     // Catch: Throwable -> L7
        StrictMode.setThreadPolicy(r1);
    L10:
        zza = r2;
        return;
    L9:
        StrictMode.setThreadPolicy(r1);
        r2 = new zzrh();
    L7:
        th = move-exception;
        StrictMode.setThreadPolicy(r1);
        throw th;
    }
}
