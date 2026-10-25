package com.google.android.gms.cast.framework.media.uicontroller;

import android.view.View;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzg implements View.OnClickListener {
    final /* synthetic */ long zza;
    final /* synthetic */ UIMediaController zzb;

    public zzg(UIMediaController r1, long r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r3) {
        long r0 = this.zza;
        this.zzb.onRewindClicked(r3, r0);
    }
}
