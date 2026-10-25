package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.framework.devicesuggestions.DeviceSuggestionResult;
import com.google.android.gms.cast.framework.devicesuggestions.DeviceSuggestionsCallback;
import com.google.android.gms.common.api.internal.ListenerHolder;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzal implements ListenerHolder.Notifier {
    final /* synthetic */ DeviceSuggestionResult zza;

    public zzal(zzam r1, DeviceSuggestionResult r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final /* bridge */ /* synthetic */ void notifyListener(Object r1) {
        ((DeviceSuggestionsCallback) r1).onDeviceSuggestionExpired(this.zza);
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final void onNotifyListenerFailed() {
        zzav.zza().w("Failed to notify listener for onDeviceSuggestionExpired", new Object[0]);
    }
}
