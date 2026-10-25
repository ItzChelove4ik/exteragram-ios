package com.google.android.gms.internal.cast;

import com.google.android.gms.internal.cast.zzwa;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;

/* loaded from: classes4.dex */
final class zzww extends zzwe implements RunnableFuture {
    private volatile zzwm zzd;

    public zzww(Callable r2) {
        this.zzd = new zzwv(this, r2);
    }

    public static zzww zzo(Runnable r1, Object r2) {
        return new zzww(Executors.callable(r1, r2));
    }

    @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
    public final void run() {
        zzwm r0 = this.zzd;
        if (r0 == null) goto L5;
        r0.run();
    L5:
        this.zzd = null;
    }

    @Override // com.google.android.gms.internal.cast.zzwa
    public final void zze() {
        Object r0 = this.valueField;
        if ((r0 instanceof zzwa.zza) == true) goto L5;
    L9:
        this.zzd = null;
        return;
    L5:
        if (((zzwa.zza) r0).zzc == false) goto L9;
        zzwm r02 = this.zzd;
        if (r02 == null) goto L9;
        r02.zze();
        goto L9
    }

    @Override // com.google.android.gms.internal.cast.zzwa
    public final String zzg() {
        zzwm r0 = this.zzd;
        if (r0 == null) goto L7;
        String r2 = r0.toString();
        StringBuilder r1 = new StringBuilder(r2.length() + 7);
        r1.append("task=[");
        r1.append(r2);
        r1.append("]");
        return r1.toString();
    L7:
        return super.zzg();
    }
}
