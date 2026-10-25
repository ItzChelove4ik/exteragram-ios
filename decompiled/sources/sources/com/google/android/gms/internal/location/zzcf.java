package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzcf implements RemoteCall, zzdq {
    final /* synthetic */ zzcg zza;
    private final zzce zzb;
    private ListenerHolder zzc;
    private boolean zzd;

    public zzcf(zzcg r1, ListenerHolder r2, zzce r3) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        this.zzd = true;
        this.zzc = r2;
        this.zzb = r3;
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* bridge */ /* synthetic */ void accept(Object r4, Object r5) {
        zzec r42 = (zzec) r4;
        TaskCompletionSource r52 = (TaskCompletionSource) r5;
        monitor-enter(this);
        ListenerHolder.ListenerKey r0 = this.zzc.getListenerKey();     // Catch: Throwable -> L11
        boolean r1 = this.zzd;     // Catch: Throwable -> L11
        this.zzc.clear();     // Catch: Throwable -> L11
        monitor-exit(this);     // Catch: Throwable -> L11
        if (r0 != null) goto L9;
        r52.setResult(Boolean.FALSE);
        return;
    L9:
        this.zzb.zza(r42, r0, r1, r52);
        return;
    L11:
        th = move-exception;
        throw th;
    }

    @Override // com.google.android.gms.internal.location.zzdq
    public final synchronized ListenerHolder zza() {
        monitor-enter(this);
        ListenerHolder r0 = this.zzc;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    @Override // com.google.android.gms.internal.location.zzdq
    public final synchronized void zzb(ListenerHolder r2) {
        monitor-enter(this);
        ListenerHolder r0 = this.zzc;     // Catch: Throwable -> L8
        if (r0 == r2) goto L10;
        r0.clear();     // Catch: Throwable -> L8
        this.zzc = r2;     // Catch: Throwable -> L8
        monitor-exit(this);
        return;
    L10:
        monitor-exit(this);
        return;
    L8:
        th = move-exception;
        throw th;
    }

    @Override // com.google.android.gms.internal.location.zzdq
    public final void zzc() {
        monitor-enter(this);
        this.zzd = false;     // Catch: Throwable -> L9
        ListenerHolder.ListenerKey<?> r0 = this.zzc.getListenerKey();     // Catch: Throwable -> L9
        monitor-exit(this);     // Catch: Throwable -> L9
        if (r0 == null) goto L14;
        this.zza.doUnregisterEventListener(r0, 2441);
        return;
    L14:
        return;
    L9:
        th = move-exception;
        throw th;
    }
}
