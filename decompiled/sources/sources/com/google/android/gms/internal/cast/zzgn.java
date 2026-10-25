package com.google.android.gms.internal.cast;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import androidx.collection.SimpleArrayMap;

/* loaded from: classes4.dex */
public abstract class zzgn extends AnimatorListenerAdapter {
    private final SimpleArrayMap zza;

    public zzgn() {
        this.zza = new SimpleArrayMap();
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator r2) {
        this.zza.put(r2, Boolean.TRUE);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator r2) {
        this.zza.put(r2, Boolean.FALSE);
    }

    public final boolean zza(Animator r2) {
        SimpleArrayMap r1 = this.zza;
        if (r1.containsKey(r2) == true) goto L5;
        return false;
    L5:
        if (((Boolean) r1.get(r2)).booleanValue() == false) goto L10;
        return true;
    L10:
        return false;
    }
}
