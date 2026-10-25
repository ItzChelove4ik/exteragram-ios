package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.common.images.WebImage;

/* loaded from: classes4.dex */
public class ImagePicker {
    public static final int IMAGE_TYPE_EXPANDED_CONTROLLER_BACKGROUND = 4;
    public static final int IMAGE_TYPE_LOCK_SCREEN_BACKGROUND = 3;
    public static final int IMAGE_TYPE_MEDIA_ROUTE_CONTROLLER_DIALOG_BACKGROUND = 0;
    public static final int IMAGE_TYPE_MINI_CONTROLLER_THUMBNAIL = 2;
    public static final int IMAGE_TYPE_NOTIFICATION_THUMBNAIL = 1;
    public static final int IMAGE_TYPE_UNKNOWN = -1;
    private final zzd zza;

    public ImagePicker() {
        this.zza = new zzi(this, null);
    }

    @Deprecated
    public WebImage onPickImage(MediaMetadata r1, int r2) {
        if (r1 != null) goto L4;
        return null;
    L4:
        if (r1.hasImages() == true) goto L7;
        return null;
    L7:
        return r1.getImages().get(0);
    }

    public WebImage onPickImage(MediaMetadata r1, ImageHints r2) {
        return onPickImage(r1, r2.getType());
    }

    public final zzd zza() {
        return this.zza;
    }
}
