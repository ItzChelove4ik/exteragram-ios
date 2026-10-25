package com.google.android.gms.internal.cast;

import android.content.Context;
import android.view.View;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzcu extends UIController {
    private final View zza;
    private final String zzb;
    private final String zzc;

    public zzcu(View r2, Context r3) {
        this.zza = r2;
        this.zzb = r3.getString(R.string.cast_closed_captions);
        this.zzc = r3.getString(R.string.cast_closed_captions_unavailable);
        r2.setEnabled(false);
    }

    private final void zza() {
        RemoteMediaClient r0 = getRemoteMediaClient();
        if (r0 != null) goto L5;
    L27:
        View r02 = this.zza;
        r02.setEnabled(false);
        r02.setContentDescription(this.zzc);
        return;
    L5:
        if (r0.hasMediaSession() == false) goto L27;
        MediaInfo r2 = r0.getMediaInfo();
        if (r2 == null) goto L27;
        List<MediaTrack> r22 = r2.getMediaTracks();
        if (r22 == null) goto L27;
        if (r22.isEmpty() == true) goto L27;
        Iterator<MediaTrack> r23 = r22.iterator();
        int r3 = 0;
    L15:
        if (r23.hasNext() == false) goto L27;
        MediaTrack r4 = r23.next();
        if (r4.getType() == 2) goto L18;
        if (r4.getType() != 1) goto L15;
    L24:
        if (r0.isPlayingAd() == true) goto L27;
        View r03 = this.zza;
        r03.setEnabled(true);
        r03.setContentDescription(this.zzb);
        return;
    L18:
        r3 = r3 + 1;
        if (r3 <= 1) goto L15;
        goto L24
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
    public final void onSessionConnected(CastSession r2) {
        super.onSessionConnected(r2);
        this.zza.setEnabled(true);
        zza();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        this.zza.setEnabled(false);
        super.onSessionEnded();
    }
}
