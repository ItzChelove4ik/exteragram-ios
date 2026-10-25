package com.google.android.gms.common.internal;

import android.content.Intent;
import com.google.android.gms.common.api.internal.LifecycleFragment;

/* loaded from: classes4.dex */
final class zai extends zaj {
    final /* synthetic */ Intent zaa;
    final /* synthetic */ LifecycleFragment zab;

    public zai(Intent r1, LifecycleFragment r2, int r3) {
        this.zaa = r1;
        this.zab = r2;
    }

    @Override // com.google.android.gms.common.internal.zaj
    public final void zaa() {
        Intent r0 = this.zaa;
        if (r0 == null) goto L6;
        this.zab.startActivityForResult(r0, 2);
        return;
    }
}
