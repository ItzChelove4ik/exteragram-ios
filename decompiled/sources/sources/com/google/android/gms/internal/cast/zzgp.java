package com.google.android.gms.internal.cast;

import android.animation.Animator;

/* loaded from: classes4.dex */
public final class zzgp extends zzgn {
    protected final Animator zza;
    private final int zzb;
    private int zzc;
    private final zzgt zzd;

    private zzgp(Animator r1, int r2, Runnable r3) {
        this.zzd = new zzgo(this);
        this.zza = r1;
        this.zzb = -1;
    }

    public static void zzb(Animator r1, int r2, Runnable r3) {
        r1.addListener(new zzgp(r1, -1, null));
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator r1) {
        if (zza(r1) == true) goto L6;
        zzgt r0 = this.zzd;
        zzgw.zzb().zza(r0);
        return;
    }

    public final /* synthetic */ boolean zzc() {
        if (this.zzb != (-1)) goto L6;
        return false;
    L6:
        if (this.zzc < 0) goto L9;
        return true;
    L9:
        return false;
    }

    public final /* synthetic */ int zzd() {
        return this.zzc;
    }

    public final /* synthetic */ void zze(int r1) {
        this.zzc = r1;
    }
}
