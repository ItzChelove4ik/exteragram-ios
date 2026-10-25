package com.google.android.gms.cast.framework.media;

import android.os.IInterface;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes4.dex */
public interface zzd extends IInterface {
    WebImage zze(MediaMetadata r1, int r2);

    IObjectWrapper zzf();

    WebImage zzg(MediaMetadata r1, ImageHints r2);
}
