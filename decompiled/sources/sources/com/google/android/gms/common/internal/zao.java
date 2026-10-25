package com.google.android.gms.common.internal;

import android.content.Context;
import android.util.SparseIntArray;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;

/* loaded from: classes4.dex */
public final class zao {
    private final SparseIntArray zaa;
    private GoogleApiAvailabilityLight zab;

    public zao(GoogleApiAvailabilityLight r2) {
        this.zaa = new SparseIntArray();
        Preconditions.checkNotNull(r2);
        this.zab = r2;
    }

    public final int zaa(Context r6, Api.Client r7) {
        Preconditions.checkNotNull(r6);
        Preconditions.checkNotNull(r7);
        int r1 = 0;
        if (r7.requiresGooglePlayServices() == true) goto L5;
        return 0;
    L5:
        int r72 = r7.getMinApkVersion();
        int r0 = zab(r6, r72);
        if (r0 == (-1)) goto L8;
        return r0;
    L8:
        SparseIntArray r02 = this.zaa;
        monitor-enter(r02);
        int r3 = 0;
    L29:
    L18:
        th = move-exception;
        throw th;
    L12:
        if (r3 >= r02.size()) goto L21;
        int r4 = r02.keyAt(r3);     // Catch: Throwable -> L18
        if (r4 <= r72) goto L20;
        if (r02.get(r4) != 0) goto L20;
    L22:
        if (r1 != (-1)) goto L24;
        r1 = this.zab.isGooglePlayServicesAvailable(r6, r72);     // Catch: Throwable -> L18
    L24:
        r02.put(r72, r1);     // Catch: Throwable -> L18
        monitor-exit(r02);     // Catch: Throwable -> L18
        return r1;
    L20:
        r3 = r3 + 1;     // Catch: Throwable -> L18
        goto L29
    L21:
        r1 = -1;
        goto L22
    }

    public final int zab(Context r1, int r2) {
        SparseIntArray r0 = this.zaa;
        monitor-enter(r0);
        int r12 = r0.get(r2, -1);     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return r12;
    L8:
        th = move-exception;
        throw th;
    }

    public final void zac() {
        SparseIntArray r1 = this.zaa;
        monitor-enter(r1);
        r1.clear();     // Catch: Throwable -> L7
        monitor-exit(r1);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
