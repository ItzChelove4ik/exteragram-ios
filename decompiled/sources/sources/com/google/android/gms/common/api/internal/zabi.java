package com.google.android.gms.common.api.internal;

import java.util.Objects;

/* loaded from: classes4.dex */
final class zabi implements Runnable {
    final /* synthetic */ zabj zaa;

    public zabi(zabj r1) {
        Objects.requireNonNull(r1);
        this.zaa = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zabk r2 = this.zaa.zaa;
        String r0 = r2.zaz().getClass().getName();
        r2.zaz().disconnect(r0.concat(" disconnecting because it was signed out."));
    }
}
