package com.google.android.gms.cast.framework.media.uicontroller;

import com.google.android.gms.cast.framework.media.widget.CastSeekBar;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzh extends com.google.android.gms.cast.framework.media.widget.zzd {
    final /* synthetic */ UIMediaController zza;

    public zzh(UIMediaController r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.cast.framework.media.widget.zzd
    public final void zza(CastSeekBar r1) {
        this.zza.zzc(r1);
    }

    @Override // com.google.android.gms.cast.framework.media.widget.zzd
    public final void zzb(CastSeekBar r1) {
        this.zza.zzd(r1);
    }

    @Override // com.google.android.gms.cast.framework.media.widget.zzd
    public final void zzc(CastSeekBar r1, int r2, boolean r3) {
        this.zza.zze(r1, r2, r3);
    }
}
