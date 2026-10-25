package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManagerListener;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzbz implements SessionManagerListener {
    final /* synthetic */ zzce zza;

    public /* synthetic */ zzbz(zzce r1, byte[] r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionEnded(Session r2, int r3) {
        CastSession r22 = (CastSession) r2;
        int r23 = zzce.$r8$clinit;
        Object[] r24 = {Integer.valueOf(r3)};
        zzce.zzo().d("onSessionEnded with error = %d", r24);
        zzce r1 = this.zza;
        r1.zzm();
        if (r1.zzp() != 2) goto L5;
        return;
    L5:
        r1.zzl();
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionEnding(Session r1) {
        CastSession r12 = (CastSession) r1;
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionResumeFailed(Session r1, int r2) {
        CastSession r12 = (CastSession) r1;
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionResumed(Session r1, boolean r2) {
        CastSession r12 = (CastSession) r1;
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionResuming(Session r1, String r2) {
        CastSession r12 = (CastSession) r1;
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionStartFailed(Session r1, int r2) {
        CastSession r12 = (CastSession) r1;
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionStarted(Session r2, String r3) {
        CastSession r22 = (CastSession) r2;
        zzce r1 = this.zza;
        Object[] r23 = {Integer.valueOf(r1.zzp())};
        zzce.zzo().d("onSessionStarted with transferType = %d", r23);
        if (r1.zzg() == true) goto L5;
    L7:
        r1.zzl();
        return;
    L5:
        if (r1.zzp() != 2) goto L7;
        r1.zzn();
        goto L7
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionStarting(Session r1) {
        CastSession r12 = (CastSession) r1;
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionSuspended(Session r1, int r2) {
        CastSession r12 = (CastSession) r1;
    }
}
