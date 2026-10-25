package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.api.internal.ListenerHolder;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzka extends zzkn {
    final /* synthetic */ ListenerHolder zza;

    public zzka(zzkk r1, ListenerHolder r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
    }

    @Override // com.google.android.gms.internal.measurement.zzko
    public final void zzb(byte[] r2) {
        zzjz r0 = new zzjz(this, r2);
        this.zza.notifyListener(r0);
    }
}
