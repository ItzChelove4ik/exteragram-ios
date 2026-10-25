package com.google.android.gms.cast.framework.media.widget;

import android.graphics.Bitmap;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzg implements com.google.android.gms.cast.framework.media.internal.zza {
    final /* synthetic */ ExpandedControllerActivity zza;

    public zzg(ExpandedControllerActivity r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.cast.framework.media.internal.zza
    public final void zza(Bitmap r3) {
        if (r3 == null) goto L10;
        ExpandedControllerActivity r2 = this.zza;
        if (r2.zzh() == null) goto L7;
        r2.zzh().setVisibility(8);
    L7:
        if (r2.zzg() == null) goto L11;
        r2.zzg().setVisibility(0);
        r2.zzg().setImageBitmap(r3);
        return;
    L11:
        return;
    }
}
