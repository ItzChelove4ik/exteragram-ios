package com.google.android.gms.cast.framework.media;

import android.os.Handler;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* loaded from: classes4.dex */
final class zzbg {
    final /* synthetic */ RemoteMediaClient zza;
    private final Set zzb;
    private final long zzc;
    private final Runnable zzd;
    private boolean zze;

    public zzbg(RemoteMediaClient r1, long r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        this.zzb = new HashSet();
        this.zzc = r2;
        this.zzd = new zzbf(this);
    }

    public final long zza() {
        return this.zzc;
    }

    public final void zzb(RemoteMediaClient.ProgressListener r1) {
        this.zzb.add(r1);
    }

    public final void zzc(RemoteMediaClient.ProgressListener r1) {
        this.zzb.remove(r1);
    }

    public final boolean zzd() {
        if (this.zzb.isEmpty() == true) goto L6;
        return true;
    L6:
        return false;
    }

    public final void zze() {
        RemoteMediaClient r0 = this.zza;
        Handler r1 = r0.zzs();
        Runnable r2 = this.zzd;
        r1.removeCallbacks(r2);
        this.zze = true;
        long r3 = this.zzc;
        r0.zzs().postDelayed(r2, r3);
    }

    public final void zzf() {
        this.zza.zzs().removeCallbacks(this.zzd);
        this.zze = false;
    }

    public final boolean zzg() {
        return this.zze;
    }

    public final /* synthetic */ Set zzh() {
        return this.zzb;
    }

    public final /* synthetic */ long zzi() {
        return this.zzc;
    }
}
