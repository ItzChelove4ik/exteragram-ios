package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzd extends AnimatorListenerAdapter {
    final /* synthetic */ zzh zza;

    public zzd(zzh r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator r1) {
        zzh r0 = this.zza;
        Animator r12 = r0.zzk();
        r0.zza = r12;
        r12.start();
    }
}
