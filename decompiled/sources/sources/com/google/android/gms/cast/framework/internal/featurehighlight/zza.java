package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.view.GestureDetector;
import android.view.MotionEvent;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zza extends GestureDetector.SimpleOnGestureListener {
    final /* synthetic */ zzh zza;

    public zza(zzh r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final boolean onSingleTapUp(MotionEvent r3) {
        zzh r2 = this.zza;
        zzg r0 = r2.zzm();
        float r1 = r3.getX();
        float r32 = r3.getY();
        if (r0 != null) goto L5;
        return true;
    L5:
        if (r2.zzj(r1, r32) == true) goto L7;
    L8:
        r2.zzm().zzb();
        return true;
    L7:
        if (r2.zzl().zzf(r1, r32) == false) goto L8;
        return true;
    }
}
