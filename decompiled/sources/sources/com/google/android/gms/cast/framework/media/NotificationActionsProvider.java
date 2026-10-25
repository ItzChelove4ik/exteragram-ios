package com.google.android.gms.cast.framework.media;

import android.content.Context;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class NotificationActionsProvider {
    private final Context zza;
    private final zzg zzb;

    public NotificationActionsProvider(Context r3) {
        this.zzb = new zzr(this, null);
        this.zza = r3.getApplicationContext();
    }

    public Context getApplicationContext() {
        return this.zza;
    }

    public abstract int[] getCompactViewActionIndices();

    public abstract List<NotificationAction> getNotificationActions();

    public final zzg zza() {
        return this.zzb;
    }
}
