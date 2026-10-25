package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzb extends GestureDetector.SimpleOnGestureListener {
    final /* synthetic */ View zza;
    final /* synthetic */ zzg zzb;

    public zzb(zzh r1, View r2, boolean r3, zzg r4) {
        this.zza = r2;
        this.zzb = r4;
        Objects.requireNonNull(r1);
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final boolean onSingleTapUp(MotionEvent r2) {
        View r22 = this.zza;
        if (r22.getParent() == null) goto L5;
        r22.performClick();
    L5:
        this.zzb.zza();
        return true;
    }
}
