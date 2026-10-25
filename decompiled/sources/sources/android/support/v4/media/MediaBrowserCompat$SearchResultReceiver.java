package android.support.v4.media;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.os.ResultReceiver;
import java.util.ArrayList;

/* loaded from: classes3.dex */
class MediaBrowserCompat$SearchResultReceiver extends ResultReceiver {
    @Override // android.support.v4.os.ResultReceiver
    public void onReceiveResult(int r4, Bundle r5) {
        if (r5 == null) goto L5;
        r5 = MediaSessionCompat.unparcelWithClassLoader(r5);
    L5:
        if (r4 != 0) goto L13;
        if (r5 != null) goto L8;
        throw null;
    L8:
        if (r5.containsKey("search_results") == false) goto L16;
        Parcelable[] r42 = r5.getParcelableArray("search_results");
        r42.getClass();
        ArrayList r52 = new ArrayList(r42.length);
        int r0 = r42.length;
        int r1 = 0;
    L10:
        if (r1 >= r0) goto L12;
        r52.add((MediaBrowserCompat$MediaItem) r42[r1]);
        r1 = r1 + 1;
        goto L10
    L12:
        throw null;
    L16:
        throw null;
    L13:
        throw null;
    }
}
