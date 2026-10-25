package com.google.android.gms.internal.cast;

import android.view.View;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* loaded from: classes4.dex */
public final class zzcv extends UIController implements RemoteMediaClient.ProgressListener {
    private final View zza;
    private final com.google.android.gms.cast.framework.media.uicontroller.zza zzb;

    public zzcv(View r1, com.google.android.gms.cast.framework.media.uicontroller.zza r2) {
        this.zza = r1;
        this.zzb = r2;
        r1.setEnabled(false);
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
    public final void onSendingRemoteMediaRequest() {
        this.zza.setEnabled(false);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession r3) {
        super.onSessionConnected(r3);
        RemoteMediaClient r32 = getRemoteMediaClient();
        if (r32 == null) goto L5;
        r32.addProgressListener(this, 1000);
    L5:
        zza();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        RemoteMediaClient r0 = getRemoteMediaClient();
        if (r0 == null) goto L5;
        r0.removeProgressListener(this);
    L5:
        this.zza.setEnabled(false);
        super.onSessionEnded();
        zza();
    }

    public final void zza() {
        RemoteMediaClient r0 = getRemoteMediaClient();
        boolean r1 = false;
        if (r0 != null) goto L5;
    L20:
        this.zza.setEnabled(false);
        return;
    L5:
        if (r0.hasMediaSession() == false) goto L20;
        if (r0.isPlayingAd() == true) goto L20;
        boolean r2 = r0.isLiveStream();
        View r3 = this.zza;
        if (r2 == true) goto L14;
        r3.setEnabled(true);
        return;
    L14:
        if (r0.zzj() == true) goto L16;
    L18:
        r3.setEnabled(r1);
        return;
    L16:
        if (this.zzb.zze() == true) goto L18;
        r1 = true;
        goto L18
    }
}
