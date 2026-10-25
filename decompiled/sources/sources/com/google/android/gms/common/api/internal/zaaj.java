package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zaaj extends zabb {
    final /* synthetic */ ConnectionResult zaa;
    final /* synthetic */ zaal zab;

    public zaaj(zaal r1, zaba r2, ConnectionResult r3) {
        this.zaa = r3;
        Objects.requireNonNull(r1);
        this.zab = r1;
        super(r2);
    }

    @Override // com.google.android.gms.common.api.internal.zabb
    public final void zaa() {
        this.zab.zaa.zao(this.zaa);
    }
}
