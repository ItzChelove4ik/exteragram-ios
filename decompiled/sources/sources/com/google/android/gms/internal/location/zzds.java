package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzds implements ListenerHolder.Notifier {
    final /* synthetic */ LocationAvailability zza;

    public zzds(zzdu r1, LocationAvailability r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final /* synthetic */ void notifyListener(Object r1) {
        ((LocationCallback) r1).onLocationAvailability(this.zza);
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final void onNotifyListenerFailed() {
    }
}
