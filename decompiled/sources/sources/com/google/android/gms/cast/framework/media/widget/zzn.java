package com.google.android.gms.cast.framework.media.widget;

import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManagerListener;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzn implements SessionManagerListener {
    final /* synthetic */ ExpandedControllerActivity zza;

    public /* synthetic */ zzn(ExpandedControllerActivity r1, byte[] r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* synthetic */ void onSessionEnded(Session r1, int r2) {
        CastSession r12 = (CastSession) r1;
        this.zza.finish();
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
    public final /* bridge */ /* synthetic */ void onSessionStarted(Session r1, String r2) {
        CastSession r12 = (CastSession) r1;
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
