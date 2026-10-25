package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationCallback;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzdt implements ListenerHolder.Notifier {
    final /* synthetic */ zzdu zza;

    public zzdt(zzdu r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final /* bridge */ /* synthetic */ void notifyListener(Object r1) {
        LocationCallback r12 = (LocationCallback) r1;
        this.zza.zzh().zzc();
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final void onNotifyListenerFailed() {
    }
}
