package com.google.android.gms.cast.framework.media.internal;

import android.graphics.Bitmap;
import android.net.Uri;
import com.google.android.gms.common.images.WebImage;

/* loaded from: classes4.dex */
final class zzl {
    public final Uri zza;
    public Bitmap zzb;

    public zzl(WebImage r1) {
        if (r1 != null) goto L5;
        Uri r12 = null;
    L6:
        this.zza = r12;
        return;
    L5:
        r12 = r1.getUrl();
        goto L6
    }
}
