package com.google.android.gms.cast.framework.media;

import java.util.Objects;
import java.util.TimerTask;

/* loaded from: classes4.dex */
final class zzj extends TimerTask {
    final /* synthetic */ MediaQueue zza;

    public zzj(MediaQueue r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        this.zza.zzf();
    }
}
