package com.google.android.gms.internal.cast;

import android.animation.Animator;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzgo extends zzgt {
    final /* synthetic */ zzgp zza;

    public zzgo(zzgp r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.cast.zzgt
    public final void zza(long r1) {
        zzgp r0 = this.zza;
        r0.zze(r0.zzd() + 1);
        Animator r12 = r0.zza;
        if (r0.zza(r12) == false) goto L5;
        return;
    L5:
        if (r12.isStarted() == false) goto L7;
        return;
    L7:
        if (r0.zzc() == true) goto L12;
        r12.start();
        return;
    }
}
