package com.google.android.gms.common.api.internal;

import java.util.Objects;

/* loaded from: classes4.dex */
final class zack implements Runnable {
    final /* synthetic */ com.google.android.gms.signin.internal.zak zaa;
    final /* synthetic */ zacm zab;

    public zack(zacm r1, com.google.android.gms.signin.internal.zak r2) {
        this.zaa = r2;
        Objects.requireNonNull(r1);
        this.zab = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zab.zae(this.zaa);
    }
}
