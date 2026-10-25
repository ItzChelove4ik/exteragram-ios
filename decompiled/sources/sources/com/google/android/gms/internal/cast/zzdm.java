package com.google.android.gms.internal.cast;

import android.view.View;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* loaded from: classes4.dex */
public final class zzdm extends UIController {
    private final View zza;
    private final int zzb;

    public zzdm(View r1, int r2) {
        this.zza = r1;
        this.zzb = r2;
        r1.setEnabled(false);
    }

    private final void zza() {
        RemoteMediaClient r0 = getRemoteMediaClient();
        if (r0 != null) goto L5;
    L10:
        View r02 = this.zza;
        r02.setVisibility(this.zzb);
        r02.setEnabled(false);
        return;
    L5:
        if (r0.zzm() == false) goto L10;
        if (r0.isPlayingAd() == true) goto L10;
        View r3 = this.zza;
        r3.setVisibility(0);
        r3.setEnabled(true);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        zza();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSendingRemoteMediaRequest() {
        this.zza.setEnabled(false);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession r1) {
        super.onSessionConnected(r1);
        zza();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        this.zza.setEnabled(false);
        super.onSessionEnded();
    }
}
