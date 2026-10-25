package com.google.android.gms.internal.measurement;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

/* loaded from: classes4.dex */
public abstract class zzvu {
    private static final WeakHashMap zza = null;
    private static final WeakHashMap zzb = null;

    static {
        zza = new WeakHashMap();
        zzb = new WeakHashMap();
    }

    public static void zza(Throwable r6) {
        WeakHashMap r0 = zzb;
        monitor-enter(r0);
        Throwable r1 = r6;
    L5:
        if (r1 == null) goto L12;
    L9:
        th = move-exception;
        throw th;
    L7:
        if (r0.containsKey(r1) == true) goto L12;
        r1 = r1.getCause();     // Catch: Throwable -> L9
    L12:
        if (r1 == null) goto L14;
        boolean r4 = true;
    L15:
        r0.put(r6, Boolean.valueOf(r4));     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        if (r1 != null) goto L69;
        Preconditions.checkState(true, "Trace uncaught exception is disabled.");
        WeakHashMap r12 = zza;
        monitor-enter(r12);
        Throwable r02 = r6;
    L22:
        if (r02 == null) goto L28;
    L26:
        th = move-exception;
        throw th;
    L24:
        if (r12.containsKey(r02) == true) goto L28;
        r02 = r02.getCause();     // Catch: Throwable -> L26
    L28:
        if (r02 != null) goto L31;
        monitor-exit(r12);     // Catch: Throwable -> L26
        zzxc r03 = null;
    L34:
        if (r03 != null) goto L52;
        zzws r04 = zzvy.zzd().zzb;
        if (r04 == null) goto L68;
        ArrayList r13 = new ArrayList();
    L38:
        if (r04 == null) goto L40;
        r13.add(r04);
        r04 = r04.zzb();
        goto L38
    L40:
        zzvo r05 = new zzvo();
        r05.zzc(((zzws) r13.get(0)).zzc());
        ((zzws) r13.get(0)).zzk();
        r05.zzd(-1);
        ImmutableList.Builder r2 = ImmutableList.builderWithExpectedSize(r13.size());
        ImmutableList.Builder r3 = ImmutableList.builderWithExpectedSize(r13.size());
        Iterator r14 = Lists.reverse(r13).iterator();
    L42:
        if (r14.hasNext() == false) goto L44;
        zzws r42 = (zzws) r14.next();
        r3.add(r42.zze());
        r2.add(r42.zzh());
        goto L42
    L44:
        WeakHashMap r15 = zza;
        monitor-enter(r15);
        r05.zza(r3.build());     // Catch: Throwable -> L49
        r05.zzb(r2.build());     // Catch: Throwable -> L49
        r15.put(r6, r05.zze());     // Catch: Throwable -> L49
        monitor-exit(r15);     // Catch: Throwable -> L49
        return;
    L49:
        th = move-exception;
        throw th;
    L68:
        return;
    L52:
        return;
    L31:
        zzww r22 = (zzww) r12.get(r02);     // Catch: Throwable -> L26
        r12.put(r6, r22);     // Catch: Throwable -> L26
        monitor-exit(r12);     // Catch: Throwable -> L26
        r03 = new zzxc(r02, r22);
        goto L34
    L69:
        return;
    L14:
        r4 = false;
        goto L15
    }
}
