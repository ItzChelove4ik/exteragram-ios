package com.google.android.gms.common.api.internal;

import java.lang.ref.WeakReference;

/* loaded from: classes4.dex */
final class zaao extends com.google.android.gms.signin.internal.zac {
    private final WeakReference zaa;

    public zaao(zaar r2) {
        this.zaa = new WeakReference(r2);
    }

    @Override // com.google.android.gms.signin.internal.zae
    public final void zab(com.google.android.gms.signin.internal.zak r3) {
        zaar r0 = (zaar) this.zaa.get();
        if (r0 != null) goto L5;
        return;
    L5:
        zaan r1 = new zaan(this, r0, r0, r3);
        r0.zar().zar(r1);
    }
}
