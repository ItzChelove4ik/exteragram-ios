package com.google.android.gms.common.api.internal;

/* loaded from: classes4.dex */
abstract class zabb {
    private final zaba zaa;

    public zabb(zaba r1) {
        this.zaa = r1;
    }

    public abstract void zaa();

    public final void zab(zabd r3) {
        r3.zat().lock();
    L7:
        th = move-exception;
        r3.zat().unlock();
        throw th;
    L4:
        if (r3.zau() != this.zaa) goto L9;
        zaa();     // Catch: Throwable -> L7
    L9:
        r3.zat().unlock();
    }
}
