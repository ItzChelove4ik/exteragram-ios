package com.google.android.gms.cast.framework.media.uicontroller;

import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;

/* loaded from: classes4.dex */
public abstract class UIController {
    private RemoteMediaClient zza;

    public UIController() {
    }

    public RemoteMediaClient getRemoteMediaClient() {
        return this.zza;
    }

    public void onMediaStatusUpdated() {
    }

    public void onSendingRemoteMediaRequest() {
    }

    public void onSessionConnected(CastSession r1) {
        if (r1 == null) goto L6;
        RemoteMediaClient r12 = r1.getRemoteMediaClient();
    L4:
        this.zza = r12;
        return;
    L6:
        r12 = null;
        goto L4
    }

    public void onSessionEnded() {
        this.zza = null;
    }
}
