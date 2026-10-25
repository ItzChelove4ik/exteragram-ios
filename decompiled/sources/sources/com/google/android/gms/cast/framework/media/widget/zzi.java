package com.google.android.gms.cast.framework.media.widget;

import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzi implements Runnable {
    final /* synthetic */ RemoteMediaClient zza;
    final /* synthetic */ zzj zzb;

    public zzi(zzj r1, RemoteMediaClient r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzb.zze(this.zza);
    }
}
