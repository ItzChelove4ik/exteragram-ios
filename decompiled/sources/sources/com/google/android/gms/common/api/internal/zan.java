package com.google.android.gms.common.api.internal;

import android.app.Dialog;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zan extends zabr {
    final /* synthetic */ Dialog zaa;
    final /* synthetic */ zao zab;

    public zan(zao r1, Dialog r2) {
        this.zaa = r2;
        Objects.requireNonNull(r1);
        this.zab = r1;
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final void zaa() {
        this.zab.zaa.zag();
        Dialog r1 = this.zaa;
        if (r1.isShowing() == false) goto L6;
        r1.dismiss();
        return;
    }
}
