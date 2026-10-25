package com.google.android.gms.internal.measurement;

import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzul implements AsyncCallable {
    final /* synthetic */ zzut zza;
    private List zzb;

    public /* synthetic */ zzul(zzut r1, byte[] r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.common.util.concurrent.AsyncCallable
    public final ListenableFuture call() {
        zzut r0 = this.zza;
        String r1 = String.valueOf(r0.zze());
        zzwi r12 = r0.zzi().zza("Initialize ".concat(r1), zzxd.zza);
        Object r2 = r0.zzh();     // Catch: Throwable -> L17
        monitor-enter(r2);     // Catch: Throwable -> L17
    L8:
        th = move-exception;
        throw th;     // Catch: Throwable -> L17
    L6:
        if (this.zzb != null) goto L10;
        this.zzb = r0.zzj();     // Catch: Throwable -> L8
        r0.zzk(Collections.EMPTY_LIST);     // Catch: Throwable -> L8
    L10:
        monitor-exit(r2);     // Catch: Throwable -> L8
        ArrayList r02 = new ArrayList(this.zzb.size());     // Catch: Throwable -> L17
        zzus r22 = new zzus(this.zza, null);     // Catch: Throwable -> L17
        Iterator r3 = this.zzb.iterator();     // Catch: Throwable -> L17
    L13:
        if (r3.hasNext() == false) goto L21;
        r02.add(((AsyncFunction) r3.next()).apply(r22));     // Catch: Throwable -> L17 Exception -> L19
    L19:
        e = move-exception;
        r02.add(Futures.immediateFailedFuture(e));     // Catch: Throwable -> L17
    L21:
        ListenableFuture r5 = Futures.whenAllSucceed(r02).call(new zzuk(this), MoreExecutors.directExecutor());     // Catch: Throwable -> L17
        r12.zza(r5);     // Catch: Throwable -> L17
        r12.close();
        return r5;
    L17:
        th = move-exception;
        r12.close();     // Catch: Throwable -> L28
    L30:
        throw th;
    L28:
        th = move-exception;
        th.addSuppressed(th);
        goto L30
    }

    public final /* synthetic */ Object zza() {
        Object r0 = this.zza.zzh();
        monitor-enter(r0);
        this.zzb = null;     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return null;
    L8:
        th = move-exception;
        throw th;
    }
}
