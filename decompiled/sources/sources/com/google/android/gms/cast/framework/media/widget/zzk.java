package com.google.android.gms.cast.framework.media.widget;

import com.google.android.gms.cast.Cast;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzk extends Cast.Listener {
    final /* synthetic */ ExpandedControllerActivity zza;

    public zzk(ExpandedControllerActivity r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.cast.Cast.Listener
    public final void onDeviceNameChanged() {
        this.zza.zzc();
    }
}
