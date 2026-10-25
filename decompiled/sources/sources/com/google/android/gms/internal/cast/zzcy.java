package com.google.android.gms.internal.cast;

import android.graphics.Bitmap;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzcy implements com.google.android.gms.cast.framework.media.internal.zza {
    final /* synthetic */ zzda zza;

    public zzcy(zzda r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.cast.framework.media.internal.zza
    public final void zza(Bitmap r3) {
        if (r3 == null) goto L10;
        zzda r2 = this.zza;
        if (r2.zzb() == null) goto L6;
        r2.zzb().setVisibility(4);
    L6:
        r2.zza().setVisibility(0);
        r2.zza().setImageBitmap(r3);
        if (r2.zzc() == null) goto L11;
        r2.zzc().zza();
        return;
    L11:
        return;
    }
}
