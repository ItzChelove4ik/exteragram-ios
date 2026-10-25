package com.google.android.gms.internal.mlkit_genai_prompt;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzgu {
    private final Object zza;
    private final List zzb;
    private boolean zzc;

    public zzgu() {
        this.zza = new Object();
        this.zzb = new ArrayList();
    }

    public final ListenableFuture zza(ListenableFuture r2, zzlc r3) {
        return CallbackToFutureAdapter.getFuture(new zzgt(this, r2, r3));
    }

    public final void zzb() {
        Object r0 = this.zza;
        monitor-enter(r0);
        this.zzc = true;     // Catch: Throwable -> L11
        List r3 = this.zzb;     // Catch: Throwable -> L11
        zzml r1 = zzml.zzq(r3);     // Catch: Throwable -> L11
        r3.clear();     // Catch: Throwable -> L11
        monitor-exit(r0);     // Catch: Throwable -> L11
        int r32 = r1.size();
        int r02 = 0;
    L8:
        if (r02 >= r32) goto L10;
        ((Runnable) r1.get(r02)).run();
        r02 = r02 + 1;
        goto L8
    L10:
        return;
    L11:
        th = move-exception;
        throw th;
    }

    public final /* synthetic */ Object zzc(ListenableFuture r3, zzlc r4, CallbackToFutureAdapter.Completer r5) {
        r5.addCancellationListener(new zzgr(r3), zzpi.zza());
        zzgs r0 = new zzgs(r5, r4);
        Object r42 = this.zza;
        monitor-enter(r42);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.zzc == false) goto L11;
        r0.run();     // Catch: Throwable -> L9
        monitor-exit(r42);     // Catch: Throwable -> L9
    L13:
        zzot.zzh(r3, new zzgq(this, r5, r0), zzpi.zza());
        return "FailureSignal.propagate";
    L11:
        this.zzb.add(r0);     // Catch: Throwable -> L9
        monitor-exit(r42);     // Catch: Throwable -> L9
        goto L13
    }

    public final /* synthetic */ void zzd(Runnable r3) {
        Object r0 = this.zza;
        monitor-enter(r0);
    L11:
        th = move-exception;
        throw th;
    L5:
        if (this.zzb.remove(r3) == false) goto L7;
    L13:
        monitor-exit(r0);     // Catch: Throwable -> L11
        return;
    L7:
        if (this.zzc == true) goto L13;
        throw new zzld();     // Catch: Throwable -> L11
    }
}
