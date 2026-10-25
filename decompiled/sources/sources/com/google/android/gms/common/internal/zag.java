package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.Intent;

/* loaded from: classes4.dex */
final class zag extends zaj {
    final /* synthetic */ Intent zaa;
    final /* synthetic */ Activity zab;
    final /* synthetic */ int zac;

    public zag(Intent r1, Activity r2, int r3) {
        this.zaa = r1;
        this.zab = r2;
        this.zac = r3;
    }

    @Override // com.google.android.gms.common.internal.zaj
    public final void zaa() {
        Intent r0 = this.zaa;
        if (r0 == null) goto L6;
        this.zab.startActivityForResult(r0, this.zac);
        return;
    }
}
