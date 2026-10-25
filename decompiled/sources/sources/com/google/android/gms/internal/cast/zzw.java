package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class zzw implements SessionManagerListener {
    final /* synthetic */ zzy zza;

    public zzw(zzy r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionEnded(Session r2, int r3) {
        CastSession r22 = (CastSession) r2;
        zzcr r23 = new zzcr(9);
        r23.zza(Integer.valueOf(r3));
        zzy r1 = this.zza;
        r23.zzb(Boolean.valueOf(r1.zzd().zze()));
        r1.zza(new zzcs(r23));
        r1.zzc();
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionEnding(Session r1) {
        CastSession r12 = (CastSession) r1;
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionResumeFailed(Session r2, int r3) {
        CastSession r22 = (CastSession) r2;
        zzcr r23 = new zzcr(8);
        r23.zza(Integer.valueOf(r3));
        zzcs r32 = new zzcs(r23);
        zzy r1 = this.zza;
        r1.zza(r32);
        r1.zzc();
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionResumed(Session r2, boolean r3) {
        zzcs r0 = new zzcs(new zzcr(4));
        zzy r1 = this.zza;
        r1.zza(r0);
        zzaa r12 = (zzaa) Preconditions.checkNotNull(r1.zze());
        r12.zzh((CastSession) r2);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionResuming(Session r3, String r4) {
        zzcs r1 = new zzcs(new zzcr(7));
        zzy r2 = this.zza;
        r2.zza(r1);
        zzaa r0 = (zzaa) Preconditions.checkNotNull(r2.zze());
        r0.zzh((CastSession) r3);
        ((zzaa) Preconditions.checkNotNull(r2.zze())).zzg(r4);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionStartFailed(Session r2, int r3) {
        CastSession r22 = (CastSession) r2;
        zzcr r23 = new zzcr(5);
        r23.zza(Integer.valueOf(r3));
        zzcs r32 = new zzcs(r23);
        zzy r1 = this.zza;
        r1.zza(r32);
        r1.zzc();
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionStarted(Session r3, String r4) {
        zzcs r1 = new zzcs(new zzcr(4));
        zzy r2 = this.zza;
        r2.zza(r1);
        zzaa r0 = (zzaa) Preconditions.checkNotNull(r2.zze());
        r0.zzh((CastSession) r3);
        ((zzaa) Preconditions.checkNotNull(r2.zze())).zzg(r4);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionStarting(Session r3) {
        CastSession r32 = (CastSession) r3;
        zzcr r0 = new zzcr(2);
        zzy r2 = this.zza;
        r0.zzb(Boolean.valueOf(r2.zzd().zze()));
        r2.zza(new zzcs(r0));
        ((zzaa) Preconditions.checkNotNull(r2.zze())).zzh(r32);
        r32.zzb(r2.zzf());
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionSuspended(Session r3, int r4) {
        zzcr r0 = new zzcr(6);
        r0.zza(Integer.valueOf(r4));
        zzcs r42 = new zzcs(r0);
        zzy r2 = this.zza;
        r2.zza(r42);
        zzaa r22 = (zzaa) Preconditions.checkNotNull(r2.zze());
        r22.zzh((CastSession) r3);
    }
}
