package com.google.android.gms.internal.measurement;

import java.lang.reflect.InvocationTargetException;

/* loaded from: classes4.dex */
abstract class zzaab {
    private static final zzaad zza = null;

    static {
        zza = zzb(zzaad.zzo());
    }

    public static /* synthetic */ zzaad zza() {
        return zza;
    }

    private static zzaad zzb(String[] r8) {
        zzaaj r1 = zzaak.zza;     // Catch: NoClassDefFoundError -> L5
    L6:
        if (r1 == null) goto L8;
        return r1;
    L8:
        StringBuilder r12 = new StringBuilder();
        int r2 = r8.length;
        int r4 = 0;
    L9:
        if (r4 >= r2) goto L19;
        String r5 = r8[r4];
        return (zzaad) Class.forName(r5).getConstructor(null).newInstance(null);
    L13:
        th = th;
        r12.append('\n');
        r12.append(r5);
        r12.append(": ");
        if ((th instanceof InvocationTargetException) == false) goto L17;
        th = th.getCause();
    L17:
        r12.append(th);
        r4 = r4 + 1;
        goto L9
    L19:
        throw new IllegalStateException(r12.insert(0, "No logging platforms found:").toString());
    L5:
        r1 = null;
        goto L6
    }
}
