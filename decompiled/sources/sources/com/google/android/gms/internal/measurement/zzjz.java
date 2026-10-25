package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.api.internal.ListenerHolder;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzjz implements ListenerHolder.Notifier {
    final /* synthetic */ byte[] zza;

    public zzjz(zzka r1, byte[] r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final /* bridge */ /* synthetic */ void notifyListener(Object r2) {
        zzpm r22 = (zzpm) r2;
        r22.zza(zzpl.zzb(this.zza, zzadf.zza()));
        return;
    L6:
        e = move-exception;
        r22.zzb(e);
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final void onNotifyListenerFailed() {
    }
}
