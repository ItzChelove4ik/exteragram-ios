package com.google.android.gms.cast.framework.media;

import android.net.Uri;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaTrack;
import java.util.Locale;

/* loaded from: classes4.dex */
public class MediaUtils {
    private MediaUtils() {
    }

    public static Uri getImageUri(MediaInfo r1, int r2) {
        if (r1 == null) goto L16;
        MediaMetadata r12 = r1.getMetadata();
        if (r12 != null) goto L7;
        return null;
    L7:
        if (r12.getImages() != null) goto L9;
        return null;
    L9:
        if (r12.getImages().size() > r2) goto L11;
        return null;
    L11:
        return r12.getImages().get(r2).getUrl();
    L16:
        return null;
    }

    public static String getImageUrl(MediaInfo r0, int r1) {
        Uri r02 = getImageUri(r0, r1);
        if (r02 != null) goto L7;
        return null;
    L7:
        return r02.toString();
    }

    @Deprecated
    public static Locale getTrackLanguage(MediaTrack r0) {
        String r02 = r0.getLanguage();
        if (r02 != null) goto L5;
        return null;
    L5:
        return Locale.forLanguageTag(r02);
    }
}
