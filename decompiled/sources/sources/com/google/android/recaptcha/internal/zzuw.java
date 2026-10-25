package com.google.android.recaptcha.internal;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes5.dex */
public final class zzuw {
    private static final zzuw zzb = null;
    private final Map zza;

    static {
        zzb = new zzuw();
    }

    public zzuw() {
        this.zza = new HashMap();
    }

    public static zzuw zza() {
        return zzb;
    }

    public final synchronized void zzb(String r6, zzsl r7) {
        monitor-enter(this);
        Map r0 = this.zza;     // Catch: Throwable -> L11
        if (r0.containsKey(r6) == true) goto L6;
        r0.put(r6, r7);     // Catch: Throwable -> L11
        monitor-exit(this);
        return;
    L6:
        if (((zzsl) r0.get(r6)).equals(r7) == false) goto L9;
        monitor-exit(this);
        return;
    L9:
        String r02 = String.valueOf(r0.get(r6));     // Catch: Throwable -> L11
        String r72 = String.valueOf(r7);     // Catch: Throwable -> L11
        StringBuilder r4 = new StringBuilder((((String.valueOf(r6).length() + 45) + r02.length()) + 17) + r72.length());     // Catch: Throwable -> L11
        r4.append("Parameters object with name ");     // Catch: Throwable -> L11
        r4.append(r6);     // Catch: Throwable -> L11
        r4.append(" already exists (");     // Catch: Throwable -> L11
        r4.append(r02);     // Catch: Throwable -> L11
        r4.append("), cannot insert ");     // Catch: Throwable -> L11
        r4.append(r72);     // Catch: Throwable -> L11
        throw new GeneralSecurityException(r4.toString());     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        throw th;
    }

    public final synchronized void zzc(Map r3) {
        monitor-enter(this);
        Iterator r32 = r3.entrySet().iterator();     // Catch: Throwable -> L8
    L4:
        if (r32.hasNext() == false) goto L10;
        Map.Entry r0 = (Map.Entry) r32.next();     // Catch: Throwable -> L8
        zzb((String) r0.getKey(), (zzsl) r0.getValue());     // Catch: Throwable -> L8
        goto L4
    L10:
        monitor-exit(this);
        return;
    L8:
        th = move-exception;
        throw th;
    }
}
