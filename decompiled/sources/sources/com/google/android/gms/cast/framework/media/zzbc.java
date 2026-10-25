package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;
import java.util.Iterator;
import java.util.Objects;

/* loaded from: classes4.dex */
abstract class zzbc extends BasePendingResult {
    private com.google.android.gms.cast.internal.zzat zza;
    private final boolean zzb;
    final /* synthetic */ RemoteMediaClient zzg;

    public zzbc(RemoteMediaClient r1, boolean r2) {
        Objects.requireNonNull(r1);
        this.zzg = r1;
        super(null);
        this.zzb = r2;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status r2) {
        return new zzbb(this, r2);
    }

    public abstract void zza();

    public final com.google.android.gms.cast.internal.zzat zzb() {
        if (this.zza != null) goto L6;
        this.zza = new zzba(this);
    L6:
        return this.zza;
    }

    public final void zzc() {
        if (this.zzb == true) goto L24;
        RemoteMediaClient r0 = this.zzg;
        Iterator r1 = r0.zzu().iterator();
    L6:
        if (r1.hasNext() == false) goto L8;
        ((RemoteMediaClient.Listener) r1.next()).onSendingRemoteMediaRequest();
        goto L6
    L8:
        Iterator r02 = r0.zzv().iterator();
    L10:
        if (r02.hasNext() == false) goto L24;
        ((RemoteMediaClient.Callback) r02.next()).onSendingRemoteMediaRequest();
    L24:
        Object r03 = this.zzg.zzr();     // Catch: com.google.android.gms.cast.internal.zzap -> L20
        monitor-enter(r03);     // Catch: com.google.android.gms.cast.internal.zzap -> L20
        zza();     // Catch: Throwable -> L17
        monitor-exit(r03);     // Catch: Throwable -> L17
        return;
    L17:
        th = move-exception;
        throw th;     // Catch: com.google.android.gms.cast.internal.zzap -> L20
    L20:
        setResult(new zzbb(this, new Status(2100)));
    }
}
