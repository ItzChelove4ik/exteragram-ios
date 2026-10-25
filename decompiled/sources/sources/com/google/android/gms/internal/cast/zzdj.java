package com.google.android.gms.internal.cast;

import android.view.View;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* loaded from: classes4.dex */
public final class zzdj extends UIController implements RemoteMediaClient.ProgressListener {
    private final View zza;
    private final com.google.android.gms.cast.framework.media.uicontroller.zza zzb;

    public zzdj(View r1, com.google.android.gms.cast.framework.media.uicontroller.zza r2) {
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
    L21:
        this.zza.setEnabled(false);
        return;
    L5:
        if (r0.hasMediaSession() == false) goto L21;
        if (r0.isPlayingAd() == true) goto L21;
        boolean r2 = r0.isLiveStream();
        View r3 = this.zza;
        if (r2 == true) goto L14;
        r3.setEnabled(true);
        return;
    L14:
        if (r0.zzj() == false) goto L19;
        com.google.android.gms.cast.framework.media.uicontroller.zza r11 = this.zzb;
        if (((r11.zzc() + r11.zzh()) - (r11.zzf() + r11.zzh())) < NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS) goto L19;
        r1 = true;
    L19:
        r3.setEnabled(r1);
    }
}
