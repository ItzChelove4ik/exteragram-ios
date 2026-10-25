package com.google.android.gms.wallet;

import android.os.Handler;
import android.os.Looper;
import android.util.SparseArray;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes5.dex */
final class zzc implements OnCompleteListener, Runnable {
    static final Handler zza = null;
    static final SparseArray zzb = null;
    private static final AtomicInteger zzd = null;
    int zzc;
    private zzd zze;
    private Task zzf;

    static {
        zza = new com.google.android.gms.internal.wallet.zzd(Looper.getMainLooper());
        zzb = new SparseArray(2);
        zzd = new AtomicInteger();
    }

    public zzc() {
    }

    public static zzc zza(Task r4) {
        zzc r0 = new zzc();
        int r1 = zzd.incrementAndGet();
        r0.zzc = r1;
        zzb.put(r1, r0);
        zza.postDelayed(r0, AutoResolveHelper.zza());
        r4.addOnCompleteListener(r0);
        return r0;
    }

    private final void zzd() {
        if (this.zzf != null) goto L5;
        return;
    L5:
        if (this.zze == null) goto L11;
        zzb.delete(this.zzc);
        zza.removeCallbacks(this);
        zzd r0 = this.zze;
        if (r0 == null) goto L12;
        zzd.zza(r0, this.zzf);
        return;
    L12:
        return;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task r1) {
        this.zzf = r1;
        zzd();
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzb.delete(this.zzc);
    }

    public final void zzb(zzd r2) {
        if (this.zze != r2) goto L6;
        this.zze = null;
        return;
    }

    public final void zzc(zzd r1) {
        this.zze = r1;
        zzd();
    }
}
