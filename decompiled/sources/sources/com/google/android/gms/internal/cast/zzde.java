package com.google.android.gms.internal.cast;

import android.widget.TextView;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzde extends UIController {
    private final TextView zza;
    private final List zzb;

    public zzde(TextView r2, List r3) {
        ArrayList r0 = new ArrayList();
        this.zzb = r0;
        this.zza = r2;
        r0.addAll(r3);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        RemoteMediaClient r0 = getRemoteMediaClient();
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.hasMediaSession() == false) goto L26;
        MediaInfo r02 = ((MediaStatus) Preconditions.checkNotNull(r0.getMediaStatus())).getMediaInfo();
        if (r02 == null) goto L24;
        MediaMetadata r03 = r02.getMetadata();
        if (r03 == null) goto L25;
        Iterator r1 = this.zzb.iterator();
    L13:
        if (r1.hasNext() == false) goto L18;
        String r2 = (String) r1.next();
        if (r03.containsKey(r2) == false) goto L13;
        this.zza.setText(r03.getString(r2));
        return;
    L18:
        this.zza.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        return;
    L25:
        return;
    L24:
        return;
    }
}
