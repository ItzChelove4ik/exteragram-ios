package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.framework.devicesuggestions.DeviceSuggestionResult;
import com.google.android.gms.common.api.internal.ListenerHolder;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzam extends zzag {
    final /* synthetic */ ListenerHolder zza;

    public zzam(zzav r1, ListenerHolder r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
    }

    @Override // com.google.android.gms.internal.cast.zzah
    public final void zzb(DeviceSuggestionResult r2) {
        zzak r0 = new zzak(this, r2);
        this.zza.notifyListener(r0);
    }

    @Override // com.google.android.gms.internal.cast.zzah
    public final void zzc(DeviceSuggestionResult r2) {
        zzal r0 = new zzal(this, r2);
        this.zza.notifyListener(r0);
    }
}
