package com.google.android.gms.common.api.internal;

import java.util.Objects;

/* loaded from: classes4.dex */
final class zaan extends zabb {
    final /* synthetic */ zaar zaa;
    final /* synthetic */ com.google.android.gms.signin.internal.zak zab;

    public zaan(zaao r1, zaba r2, zaar r3, com.google.android.gms.signin.internal.zak r4) {
        this.zaa = r3;
        this.zab = r4;
        Objects.requireNonNull(r1);
        super(r2);
    }

    @Override // com.google.android.gms.common.api.internal.zabb
    public final void zaa() {
        this.zaa.zaj(this.zab);
    }
}
