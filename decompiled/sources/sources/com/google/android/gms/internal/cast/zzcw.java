package com.google.android.gms.internal.cast;

import android.graphics.Bitmap;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzcw implements com.google.android.gms.cast.framework.media.internal.zza {
    final /* synthetic */ zzcx zza;

    public zzcw(zzcx r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.cast.framework.media.internal.zza
    public final void zza(Bitmap r1) {
        if (r1 == null) goto L5;
        this.zza.zza().setImageBitmap(r1);
        return;
    }
}
