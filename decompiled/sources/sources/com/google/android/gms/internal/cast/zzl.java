package com.google.android.gms.internal.cast;

import com.android.dx.io.Opcodes;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class zzl implements SessionManagerListener {
    final /* synthetic */ zzn zza;

    public zzl(zzn r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionEnded(Session r1, int r2) {
        zzn r0 = this.zza;
        r0.zzo((CastSession) r1);
        r0.zzh(r2);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* synthetic */ void onSessionEnding(Session r1) {
        this.zza.zzo((CastSession) r1);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionResumeFailed(Session r1, int r2) {
        zzn r0 = this.zza;
        r0.zzo((CastSession) r1);
        r0.zzh(r2);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionResumed(Session r4, boolean r5) {
        int r0 = zzn.$r8$clinit;
        Object[] r02 = {Boolean.valueOf(r5)};
        zzn.zzi().d("onSessionResumed with wasSuspended = %b", r02);
        zzn r3 = this.zza;
        r3.zzo((CastSession) r4);
        r3.zze();
        Preconditions.checkNotNull(r3.zzm());
        zzp r42 = r3.zzk();
        zzo r03 = r3.zzm();
        r3.zzj().zzd(r42.zzd(r03, r5), 227);
        r3.zzg();
        r3.zzb();
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionResuming(Session r4, String r5) {
        Logger r1 = zzn.zzi();
        r1.d("onSessionResuming with sessionId = %s", new Object[]{r5});
        zzn r3 = this.zza;
        r3.zzo((CastSession) r4);
        r3.zzf(r3.zzl(), r5);
        Preconditions.checkNotNull(r3.zzm());
        zzp r42 = r3.zzk();
        zzo r52 = r3.zzm();
        r3.zzj().zzd(r42.zzc(r52), Opcodes.USHR_INT_LIT8);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionStartFailed(Session r1, int r2) {
        zzn r0 = this.zza;
        r0.zzo((CastSession) r1);
        r0.zzh(r2);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionStarted(Session r4, String r5) {
        Logger r1 = zzn.zzi();
        r1.d("onSessionStarted with sessionId = %s", new Object[]{r5});
        zzn r3 = this.zza;
        r3.zzo((CastSession) r4);
        r3.zze();
        r3.zzm().zzf = r5;
        zzp r42 = r3.zzk();
        zzo r52 = r3.zzm();
        r3.zzj().zzd(r42.zza(r52), Opcodes.OR_INT_LIT8);
        r3.zzg();
        r3.zzb();
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionStarting(Session r5) {
        zzn.zzi().d("onSessionStarting", new Object[0]);
        zzn r4 = this.zza;
        r4.zzo((CastSession) r5);
        if (r4.zzm() == null) goto L5;
        Logger r0 = zzn.zzi();
        r0.w("Start a session while there's already an active session. Create a new one.", new Object[0]);
    L5:
        r4.zzd();
        zzo r52 = r4.zzm();
        zzp r02 = r4.zzk();
        r4.zzj().zzd(r02.zzb(r52), Opcodes.AND_INT_LIT8);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionSuspended(Session r4, int r5) {
        int r0 = zzn.$r8$clinit;
        Object[] r02 = {Integer.valueOf(r5)};
        zzn.zzi().d("onSessionSuspended with reason = %d", r02);
        zzn r3 = this.zza;
        r3.zzo((CastSession) r4);
        r3.zze();
        Preconditions.checkNotNull(r3.zzm());
        zzp r42 = r3.zzk();
        zzo r03 = r3.zzm();
        r3.zzj().zzd(r42.zze(r03, r5), Opcodes.SHR_INT_LIT8);
        r3.zzg();
        r3.zzc();
    }
}
