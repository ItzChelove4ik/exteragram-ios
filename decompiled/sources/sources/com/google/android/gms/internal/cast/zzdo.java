package com.google.android.gms.internal.cast;

import android.widget.TextView;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* loaded from: classes4.dex */
public final class zzdo extends UIController {
    private final TextView zza;

    public zzdo(TextView r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        RemoteMediaClient r0 = getRemoteMediaClient();
        if (r0 == null) goto L16;
        MediaInfo r02 = r0.getMediaInfo();
        if (r02 == null) goto L13;
        MediaMetadata r03 = r02.getMetadata();
        if (r03 == null) goto L14;
        String r04 = com.google.android.gms.cast.framework.media.internal.zzt.zza(r03);
        if (r04 == null) goto L15;
        this.zza.setText(r04);
        return;
    L15:
        return;
    L14:
        return;
    L13:
        return;
    }
}
