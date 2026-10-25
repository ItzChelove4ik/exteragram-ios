package com.google.android.gms.internal.cast;

import android.view.View;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes4.dex */
public final class zzds extends UIController {
    private final View zza;
    private final int zzb;

    public zzds(View r1, int r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    private final void zza() {
        RemoteMediaClient r0 = getRemoteMediaClient();
        if (r0 != null) goto L5;
    L13:
        this.zza.setVisibility(this.zzb);
        return;
    L5:
        if (r0.hasMediaSession() == false) goto L13;
        int r02 = ((MediaStatus) Preconditions.checkNotNull(r0.getMediaStatus())).getPreloadedItemId();
        View r1 = this.zza;
        if (r02 != 0) goto L11;
        r1.setVisibility(this.zzb);
        return;
    L11:
        r1.setVisibility(0);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        zza();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession r1) {
        super.onSessionConnected(r1);
        zza();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        this.zza.setVisibility(this.zzb);
        super.onSessionEnded();
    }
}
