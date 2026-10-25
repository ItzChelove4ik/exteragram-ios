package android.support.v4.media;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.os.ResultReceiver;

/* loaded from: classes3.dex */
class MediaBrowserCompat$ItemReceiver extends ResultReceiver {
    @Override // android.support.v4.os.ResultReceiver
    public void onReceiveResult(int r2, Bundle r3) {
        if (r3 == null) goto L5;
        r3 = MediaSessionCompat.unparcelWithClassLoader(r3);
    L5:
        if (r2 != 0) goto L17;
        if (r3 != null) goto L8;
        throw null;
    L8:
        if (r3.containsKey("media_item") == false) goto L19;
        Parcelable r22 = r3.getParcelable("media_item");
        if (r22 != null) goto L12;
    L15:
        MediaBrowserCompat$MediaItem r23 = (MediaBrowserCompat$MediaItem) r22;
        throw null;
    L12:
        if ((r22 instanceof MediaBrowserCompat$MediaItem) == true) goto L15;
        throw null;
    L19:
        throw null;
    L17:
        throw null;
    }
}
