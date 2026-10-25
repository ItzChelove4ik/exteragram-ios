package com.google.android.gms.cast.framework.media;

import android.content.DialogInterface;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzbh implements DialogInterface.OnClickListener {
    final /* synthetic */ TracksChooserDialogFragment zza;

    public zzbh(TracksChooserDialogFragment r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface r1, int r2) {
        TracksChooserDialogFragment r0 = this.zza;
        if (r0.zzb() == null) goto L6;
        r0.zzb().cancel();
        r0.zzc(null);
        return;
    }
}
