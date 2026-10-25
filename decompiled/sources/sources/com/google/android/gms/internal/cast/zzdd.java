package com.google.android.gms.internal.cast;

import android.widget.TextView;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzdd extends UIController {
    private final TextView zza;
    private final List zzb;

    public zzdd(TextView r2, List r3) {
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
        if (r0.hasMediaSession() == false) goto L29;
        MediaQueueItem r02 = r0.getPreloadedItem();
        if (r02 == null) goto L26;
        MediaInfo r03 = r02.getMedia();
        if (r03 == null) goto L27;
        MediaMetadata r04 = r03.getMetadata();
        if (r04 == null) goto L28;
        Iterator r1 = this.zzb.iterator();
    L15:
        if (r1.hasNext() == false) goto L20;
        String r2 = (String) r1.next();
        if (r04.containsKey(r2) == false) goto L15;
        this.zza.setText(r04.getString(r2));
        return;
    L20:
        this.zza.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        return;
    L28:
        return;
    L27:
        return;
    L26:
        return;
    }
}
