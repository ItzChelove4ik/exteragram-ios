package com.google.android.gms.internal.cast;

import com.google.android.gms.internal.cast.zzwa;

/* loaded from: classes4.dex */
final class zzwr extends zzwa.zzf implements Runnable {
    private final Runnable zzd;

    public zzwr(Runnable r1) {
        r1.getClass();
        this.zzd = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzd.run();     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        zzd(th);
        throw th;
    }

    @Override // com.google.android.gms.internal.cast.zzwa
    public final String zzg() {
        String r2 = this.zzd.toString();
        StringBuilder r1 = new StringBuilder(r2.length() + 7);
        r1.append("task=[");
        r1.append(r2);
        r1.append("]");
        return r1.toString();
    }
}
