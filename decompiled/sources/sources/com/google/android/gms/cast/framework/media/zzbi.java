package com.google.android.gms.cast.framework.media;

import android.content.DialogInterface;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzbi implements DialogInterface.OnClickListener {
    final /* synthetic */ zzbk zza;
    final /* synthetic */ zzbk zzb;
    final /* synthetic */ TracksChooserDialogFragment zzc;

    public zzbi(TracksChooserDialogFragment r1, zzbk r2, zzbk r3) {
        this.zza = r2;
        this.zzb = r3;
        Objects.requireNonNull(r1);
        this.zzc = r1;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface r1, int r2) {
        this.zzc.zza(this.zza, this.zzb);
    }
}
