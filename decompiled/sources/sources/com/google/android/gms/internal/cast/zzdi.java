package com.google.android.gms.internal.cast;

import android.widget.ProgressBar;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* loaded from: classes4.dex */
public final class zzdi extends UIController implements RemoteMediaClient.ProgressListener {
    private final ProgressBar zza;
    private final long zzb;

    public zzdi(ProgressBar r1, long r2) {
        this.zza = r1;
        this.zzb = r2;
        r1.setMax(1);
        r1.setProgress(0);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        zza();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.ProgressListener
    public final void onProgressUpdated(long r1, long r3) {
        zza();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession r3) {
        super.onSessionConnected(r3);
        RemoteMediaClient r32 = getRemoteMediaClient();
        if (r32 == null) goto L5;
        r32.addProgressListener(this, this.zzb);
    L5:
        zza();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        RemoteMediaClient r0 = getRemoteMediaClient();
        if (r0 == null) goto L5;
        r0.removeProgressListener(this);
    L5:
        super.onSessionEnded();
        zza();
    }

    public final void zza() {
        RemoteMediaClient r0 = getRemoteMediaClient();
        if (r0 != null) goto L5;
    L11:
        ProgressBar r3 = this.zza;
        r3.setMax(1);
        r3.setProgress(0);
        return;
    L5:
        if (r0.hasMediaSession() == false) goto L11;
        if (r0.isLiveStream() == true) goto L11;
        ProgressBar r32 = this.zza;
        r32.setMax((int) r0.getStreamDuration());
        r32.setProgress((int) r0.getApproximateStreamPosition());
    }
}
