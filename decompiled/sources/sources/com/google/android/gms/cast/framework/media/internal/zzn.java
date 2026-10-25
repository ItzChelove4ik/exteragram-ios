package com.google.android.gms.cast.framework.media.internal;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzn implements zza {
    final /* synthetic */ zzs zza;

    public zzn(zzs r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.cast.framework.media.internal.zza
    public final void zza(Bitmap r9) {
        int r0 = zzs.zza;
        Bitmap r02 = null;
        if (r9 == null) goto L9;
        int r1 = r9.getWidth();
        float r2 = r1;
        int r4 = (int) (((9.0f * r2) / 16.0f) + 0.5f);
        float r5 = (r4 - r3) / 2.0f;
        RectF r6 = new RectF(0.0f, r5, r2, r9.getHeight() + r5);
        Bitmap.Config r22 = r9.getConfig();
        if (r22 != null) goto L8;
        r22 = Bitmap.Config.ARGB_8888;
    L8:
        Bitmap r12 = Bitmap.createBitmap(r1, r4, r22);
        new Canvas(r12).drawBitmap(r9, null, r6, null);
        r02 = r12;
    L9:
        this.zza.zze(r02, 0);
    }
}
