package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.view.View;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzc implements View.OnLayoutChangeListener {
    final /* synthetic */ zzh zza;

    public zzc(zzh r1, Runnable r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
        zzh r12 = this.zza;
        r12.zzc();
        r12.removeOnLayoutChangeListener(this);
    }
}
