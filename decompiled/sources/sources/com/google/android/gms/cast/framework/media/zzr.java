package com.google.android.gms.cast.framework.media;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.List;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzr extends zzf {
    final /* synthetic */ NotificationActionsProvider zza;

    public /* synthetic */ zzr(NotificationActionsProvider r1, byte[] r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.cast.framework.media.zzg
    public final IObjectWrapper zze() {
        return ObjectWrapper.wrap(this.zza);
    }

    @Override // com.google.android.gms.cast.framework.media.zzg
    public final List zzf() {
        return this.zza.getNotificationActions();
    }

    @Override // com.google.android.gms.cast.framework.media.zzg
    public final int[] zzg() {
        return this.zza.getCompactViewActionIndices();
    }
}
